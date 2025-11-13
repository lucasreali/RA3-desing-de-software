
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PaymentMethodRepository {
    private final EntityManager entityManager;

    @Autowired
    public PaymentMethodRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
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

            PaymentMethod paymentMethod = findPaymentMethodById(id)
                    .orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found with id: " + id));

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Optional<PaymentMethod> findPaymentMethodById(String id) {
        try {
            Query query =
                    entityManager.createQuery("select p from PaymentMethod p " +
                            "where" +
                            " " +
                            "p" +
                            ".id" +
                            " = :id");
            query.setParameter("id", id);
            return Optional.of((PaymentMethod) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            PaymentMethod paymentMethod = findPaymentMethodById(id)
                    .orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found with id: " + id));
            entityManager.remove(paymentMethod);
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
