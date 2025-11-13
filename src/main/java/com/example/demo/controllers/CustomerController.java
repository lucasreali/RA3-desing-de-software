package com.example.demo.controllers;

import com.example.demo.dtos.customer.CreateCustomerDTO;
import com.example.demo.dtos.customer.ResponseCustomerDTO;
import com.example.demo.dtos.customer.UpdateCustomerDTO;
import com.example.demo.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ResponseCustomerDTO> create(@Valid @RequestBody CreateCustomerDTO createCustomerDTO) {
        ResponseCustomerDTO responseCustomerDTO = customerService.create(createCustomerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCustomerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCustomerDTO> findById(@PathVariable UUID id) {
        ResponseCustomerDTO responseCustomerDTO = customerService.findById(id);
        return ResponseEntity.ok(responseCustomerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCustomerDTO> update(@PathVariable String id, @Valid @RequestBody UpdateCustomerDTO updateCustomerDTO) {
        ResponseCustomerDTO responseCustomerDTO = customerService.update(id, updateCustomerDTO);
        return ResponseEntity.ok(responseCustomerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
