package com.POC.open_finance_customer_service.application.port.out;

import com.POC.open_finance_customer_service.domain.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepositoryPort {

    Mono<Customer> save(Customer customer);

    Mono<Customer> findById(String id);

    Flux<Customer> findAll();

    Mono<Customer> findByDocumentNumber(String documentNumber);

    Mono<Void> deleteById(String id);

    Mono<Boolean> existsByDocumentNumber(String documentNumber);
}
