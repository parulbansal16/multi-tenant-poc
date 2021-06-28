package com.sense.writeback.tenant.controller;

import com.sense.writeback.tenant.models.Customer;
import com.sense.writeback.tenant.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customers;

    @GetMapping("")
    public Flux<Customer> all() {
        return this.customers.findAll();
    }


    @PostMapping("")
    public Mono<Customer> create(@RequestBody Customer customer) {
        return this.customers.save(customer);

    }

}

