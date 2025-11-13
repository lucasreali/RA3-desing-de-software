package com.example.demo.dtos.customer;

import com.example.demo.models.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResponseCustomerDTO {
    private final UUID id;
    private final String name;
    private final String cpf;
    private final String email;

    public ResponseCustomerDTO(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        cpf = customer.getCpf();
        email = customer.getEmail();
    }
}
