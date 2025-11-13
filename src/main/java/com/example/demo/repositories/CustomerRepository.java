
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerRepository {
    private final EntityManager entityManager;

    @Autowired
    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.persist(customer);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void edit(String id, String name, String cpf, String Email) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Customer customer = findCustomerById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
            customer.setName(name);
            customer.setCpf(cpf);
            customer.setEmail(Email);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Optional<Customer> findCustomerById(String id) {
        try {
            Query query =
                    entityManager.createQuery("select c from Customer c where c" +
                            ".id" +
                            " = :id");
            query.setParameter("id", id);
            return Optional.of((Customer) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Customer customer = findCustomerById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
            entityManager.remove(customer);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    @PreDestroy
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

}
