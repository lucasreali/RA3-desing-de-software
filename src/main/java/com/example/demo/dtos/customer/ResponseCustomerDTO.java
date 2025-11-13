package com.example.demo.dtos.customer;

import com.example.demo.models.Customer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponseCustomerDTO {
    private final UUID id;
    private final String name;
    private final String cpf;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public ResponseCustomerDTO(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        cpf = customer.getCpf();
        email = customer.getEmail();
        createdAt = customer.getCreatedAt();
        updatedAt = customer.getUpdatedAt();
    }
}
