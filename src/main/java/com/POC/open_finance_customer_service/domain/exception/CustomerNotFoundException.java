package com.POC.open_finance_customer_service.domain.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String id) {
        super(String.format("Cliente não encontrado com ID: %s", id));
    }

}
