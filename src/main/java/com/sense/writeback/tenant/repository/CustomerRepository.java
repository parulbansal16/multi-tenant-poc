package com.sense.writeback.tenant.repository;

import com.sense.writeback.tenant.models.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
}