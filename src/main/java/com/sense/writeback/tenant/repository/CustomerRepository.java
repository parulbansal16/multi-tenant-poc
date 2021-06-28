package com.sense.writeback.tenant.repository;

import com.sense.writeback.tenant.models.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
}