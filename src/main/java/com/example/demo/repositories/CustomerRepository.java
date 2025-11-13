
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.persistence.*;

public class CustomerRepository {
    EntityManagerFactory emFactory;
    EntityManager entityManager;

    public CustomerRepository() {
        emFactory = Persistence.createEntityManagerFactory("customerEmFactory");
        entityManager = emFactory.createEntityManager();
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

            Customer customer = findCustomerById(id);
            customer.setName(name);
            customer.setCpf(cpf);
            customer.setEmail(Email);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Customer findCustomerById(String id) {
        Query query =
                entityManager.createQuery("select c from Customer c where c" +
                        ".id" +
                        " = :id");
        query.setParameter("id", id);
        return (Customer) query.getSingleResult();
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Customer customer = findCustomerById(id);
            entityManager.remove(customer);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void close() {
        if (entityManager.isOpen()) entityManager.close();
        if (emFactory.isOpen()) emFactory.close();
    }

}
