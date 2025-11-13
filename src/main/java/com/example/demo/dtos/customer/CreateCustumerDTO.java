package com.example.demo.dtos.customer;

import com.example.demo.models.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustumerDTO {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    @Email
    private String email;

    public Customer convertToEntity() {
        Customer customer = new Customer();

        customer.setCpf(cpf);
        customer.setName(name);
        customer.setEmail(email);

        return customer;
    }
}
