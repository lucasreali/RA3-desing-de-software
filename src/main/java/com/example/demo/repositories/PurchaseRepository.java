
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PurchaseRepository {
    private final EntityManager entityManager;

    @Autowired
    public PurchaseRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(Purchase purchase) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.persist(purchase);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void edit(String id, int installment) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Purchase purchase = findPurchaseById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Purchase not found with id: " + id));
            purchase.setInstallment(installment);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Optional<Purchase> findPurchaseById(String id) {
        try {
            Query query =
                    entityManager.createQuery("select p from Purchase p where p" +
                            ".id" +
                            " = :id");
            query.setParameter("id", id);
            return Optional.of((Purchase) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Purchase purchase = findPurchaseById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Purchase not found with id: " + id));
            entityManager.remove(purchase);
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

