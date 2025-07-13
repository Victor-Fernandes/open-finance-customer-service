package com.POC.open_finance_customer_service.domain.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "customer")
public class Customer {

    @Id
    private String id;

    private String name;
    private String documentNumber;
    private String email;
    private String phoneNumber;
    private Address address;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean consentGiven;
    private CustomerStatus status;

    public enum CustomerStatus {
        ACTIVE,
        INACTIVE,
        PENDING,
        BLOCKED
    }
}
