package com.sense.writeback.master;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TenantConfigRepository extends R2dbcRepository<TenantConfigEntity, Long> {
}