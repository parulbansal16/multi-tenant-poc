package com.sense.writeback.tenant.interceptor;

import com.sense.writeback.tenant.config.CurrentTenantIdHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class TenantFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String value = exchange.getRequest().getHeaders().getFirst("X-TenantId");
        if (StringUtils.hasText(value)) {
            return chain.filter(exchange)
                    .contextWrite(CurrentTenantIdHolder.withTenantId(value));
        }
        return chain.filter(exchange);
    }
}