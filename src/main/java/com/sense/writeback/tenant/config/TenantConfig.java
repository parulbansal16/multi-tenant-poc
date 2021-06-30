package com.sense.writeback.tenant.config;

import com.sense.writeback.master.TenantConfigRepository;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.DialectResolver;
import org.springframework.data.r2dbc.dialect.R2dbcDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.HashMap;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.sense.writeback.tenant.repository", entityOperationsRef = "tenantEntityTemplate")
public class TenantConfig {

    @Value("${r2dbc.database.url}")
    private String databaseUrl;

    @Autowired
    @Qualifier(value = "masterConnectionFactory")
    ConnectionFactory fallbackConnectionFactory;

    @Autowired
    private TenantConfigRepository configRepository;

    //these tenants for now are being stored in database, later can be stored in properties files as well
    private final HashMap<Object, Object> tenantConnectionFactoriesMap = new HashMap<>();

    @PostConstruct
    public void initializeTenantDataSources() {
        this.initializeTenantConnectionFactoriesMap();
    }

    private void initializeTenantConnectionFactoriesMap() {
        configRepository.findAll(Sort.by("tenantId"))
                .doOnNext(
                        data -> {
                            String  tenantId = data.getTenantId();
                            tenantConnectionFactoriesMap.putIfAbsent(tenantId, ConnectionFactories.get(databaseUrl+tenantId));
                        }
                )
                .blockLast(Duration.ofSeconds(5));

    }

    @Bean()
    @Qualifier("tenantConnectionFactory")
    public ConnectionFactory tenantConnectionFactory() {
        TenantAwareConnectionFactory tenantConnectionFactory = new TenantAwareConnectionFactory();
        tenantConnectionFactory.setDefaultTargetConnectionFactory(fallbackConnectionFactory);
        tenantConnectionFactory.setTargetConnectionFactories(tenantConnectionFactoriesMap);
        return tenantConnectionFactory;
    }

    @Bean
    public R2dbcEntityOperations tenantEntityTemplate(@Qualifier("tenantConnectionFactory") ConnectionFactory connectionFactory) {

        R2dbcDialect dialect = DialectResolver.getDialect(connectionFactory);
        DefaultReactiveDataAccessStrategy strategy = new DefaultReactiveDataAccessStrategy(dialect);
        DatabaseClient databaseClient = DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .bindMarkers(dialect.getBindMarkersFactory())
                .build();

        return new R2dbcEntityTemplate(databaseClient, strategy);
    }
}