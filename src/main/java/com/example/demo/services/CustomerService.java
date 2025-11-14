package com.example.demo.services;

import com.example.demo.dtos.customer.CreateCustomerDTO;
import com.example.demo.dtos.customer.ResponseCustomerDTO;
import com.example.demo.dtos.customer.UpdateCustomerDTO;
import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public ResponseCustomerDTO create(CreateCustomerDTO createCustomerDTO) {
        Customer customer = createCustomerDTO.convertToEntity();
        customerRepository.save(customer);
        return new ResponseCustomerDTO(customer);
    }

    @Transactional(readOnly = true)
    public ResponseCustomerDTO findById(UUID id) {
        Customer customer = customerRepository.findCustomerById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        return new ResponseCustomerDTO(customer);
    }

    @Transactional
    public ResponseCustomerDTO update(UUID id, UpdateCustomerDTO updateCustomerDTO) {
        Customer costumer = updateCustomerDTO.convertToEntity();
        costumer.setId(id);
        Customer updateCostumer = customerRepository.edit(costumer);
        return new ResponseCustomerDTO(updateCostumer);
    }

    @Transactional
    public void delete(UUID id) {
        customerRepository.delete(id);
    }
}
