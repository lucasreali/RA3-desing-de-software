package com.example.demo.controllers;

import com.example.demo.dtos.customer.CreateCustumerDTO;
import com.example.demo.dtos.customer.ResponseCustomerDTO;
import com.example.demo.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ResponseCustomerDTO> create(@Valid @RequestBody CreateCustumerDTO createCustumerDTO) {
        ResponseCustomerDTO responseCustomerDTO = customerService.create(createCustumerDTO);
        return ResponseEntity.ok(responseCustomerDTO);
    }
}
