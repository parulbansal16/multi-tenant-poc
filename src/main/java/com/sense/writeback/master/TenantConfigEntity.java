package com.sense.writeback.master;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TENANT_CONFIGS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantConfigEntity {

    @Id
    @Column("ID")
    private Integer id;

    @Column("TENANT_ID")
    private String tenantId;

    @Column("URL")
    private String url;
}