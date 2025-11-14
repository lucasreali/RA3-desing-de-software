package com.example.demo.repositories;


import com.example.demo.models.Customer;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    public Customer edit(Customer customer) {
        Customer updateDcustomer = findCustomerById(customer.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customer.getId()));

        if (customer.getName() != null) {
            updateDcustomer.setName(customer.getName());
        }
        if (customer.getCpf() != null) {
            updateDcustomer.setCpf(customer.getCpf());
        }
        if (customer.getEmail() != null) {
            updateDcustomer.setEmail(customer.getEmail());
        }

        return updateDcustomer;
    }

    public Optional<Customer> findCustomerById(UUID id) {
        try {
            Query query =
                    entityManager.createQuery("select c from Customer c where c.id = :id");
            query.setParameter("id", id);
            return Optional.of((Customer) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void delete(UUID id) {
        Customer customer = findCustomerById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        entityManager.remove(customer);
    }
}
