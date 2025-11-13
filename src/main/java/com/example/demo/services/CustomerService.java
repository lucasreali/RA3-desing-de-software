package com.example.demo.services;

import com.example.demo.dtos.customer.CreateCustomerDTO;
import com.example.demo.dtos.customer.ResponseCustomerDTO;
import com.example.demo.dtos.customer.UpdateCustomerDTO;
import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseCustomerDTO create(CreateCustomerDTO createCustomerDTO) {
        Customer customer = createCustomerDTO.convertToEntity();
        customerRepository.save(customer);
        return new ResponseCustomerDTO(customer);
    }

    public ResponseCustomerDTO findById(UUID id) {
        Customer customer = customerRepository.findCustomerById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        return new ResponseCustomerDTO(customer);
    }

    public ResponseCustomerDTO update(String id, UpdateCustomerDTO updateCustomerDTO) {
        Customer costumer = customerRepository.edit(updateCustomerDTO.convertToEntity());
        return new ResponseCustomerDTO(costumer);
    }

    public void delete(UUID id) {
        customerRepository.delete(id);
    }
}
