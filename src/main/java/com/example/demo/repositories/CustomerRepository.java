package com.example.demo.repositories;

import com.example.demo.models.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepository {

    private final EntityManagerFactory emf;

    public CustomerRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Customer customer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();
            em.persist(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public Customer edit(Customer customer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            Customer updatedCustomer = em.find(Customer.class, customer.getId());

            if (updatedCustomer == null) {
                throw new EntityNotFoundException("Customer not found with id: " + customer.getId());
            }

            if (customer.getName() != null) updatedCustomer.setName(customer.getName());
            if (customer.getCpf() != null) updatedCustomer.setCpf(customer.getCpf());
            if (customer.getEmail() != null) updatedCustomer.setEmail(customer.getEmail());

            tx.commit();
            return updatedCustomer;

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }

    public Optional<Customer> findCustomerById(UUID id) {

        try (EntityManager em = emf.createEntityManager()) {
            Customer customer = em.find(Customer.class, id);
            return Optional.ofNullable(customer);
        }
    }

    public void delete(UUID id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            Customer customer = em.find(Customer.class, id);

            if (customer == null) {
                throw new EntityNotFoundException("Customer not found with id: " + id);
            }

            em.remove(customer);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }
}