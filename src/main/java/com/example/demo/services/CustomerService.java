package com.example.demo.services;

import com.example.demo.dtos.customer.CreateCustumerDTO;
import com.example.demo.dtos.customer.ResponseCustomerDTO;
import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseCustomerDTO create(CreateCustumerDTO createCustumerDTO) {
        Customer customer = createCustumerDTO.convertToEntity();
        customerRepository.save(customer);
        return new ResponseCustomerDTO(customer);
    }


}
