package com.sense.writeback.tenant.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table(value = "CUSTOMERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @Id
    @Generated
    @Column(value = "ID")
    private Integer id;

    @Column(value = "FIRST_NAME")
    private String firstName;

    @Column(value = "LAST_NAME")
    private String lastName;
}