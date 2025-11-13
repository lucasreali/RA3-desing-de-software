
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.persistence.*;

public class PaymentMethodRepository {
    EntityManagerFactory emFactory;
    EntityManager entityManager;

    public PaymentMethodRepository() {
        emFactory = Persistence.createEntityManagerFactory(
                "paymentMethodEmFactory");
        entityManager = emFactory.createEntityManager();
    }

    public void save(PaymentMethod paymentMethod) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.persist(paymentMethod);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void edit(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            PaymentMethod paymentMethod = findPaymentMethodById(id);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public PaymentMethod findPaymentMethodById(String id) {
        Query query =
                entityManager.createQuery("select p from PaymentMethod p " +
                        "where" +
                        " " +
                        "p" +
                        ".id" +
                        " = :id");
        query.setParameter("id", id);
        return (PaymentMethod) query.getSingleResult();
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            PaymentMethod paymentMethod = findPaymentMethodById(id);
            entityManager.remove(paymentMethod);
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
