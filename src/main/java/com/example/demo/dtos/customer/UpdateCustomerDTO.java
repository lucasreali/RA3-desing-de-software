package com.example.demo.dtos.customer;

import com.example.demo.models.Customer;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerDTO {
    private String name;
    private String cpf;
    @Email
    private String email;

    public Customer convertToEntity() {
        Customer customer = new Customer();

        customer.setCpf(cpf != null && !cpf.isBlank() ? cpf : null);
        customer.setName(name != null && !name.isBlank() ? name : null);
        customer.setEmail(email != null && !email.isBlank() ? email : null);

        return customer;
    }
}
