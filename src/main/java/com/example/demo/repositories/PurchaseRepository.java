
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.persistence.*;


public class PurchaseRepository {
    EntityManagerFactory emFactory;
    EntityManager entityManager;

    public PurchaseRepository(){
        emFactory = Persistence.createEntityManagerFactory("purchaseEmFactory");
        entityManager = emFactory.createEntityManager();
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

            Purchase purchase = findPurchaseById(id);
            purchase.setInstallment(installment);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Purchase findPurchaseById(String id) {
        Query query =
                entityManager.createQuery("select p from Purchase p where p" +
                        ".id" +
                        " = :id");
        query.setParameter("id", id);
        return (Purchase) query.getSingleResult();
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Purchase purchase = findPurchaseById(id);
            entityManager.remove(purchase);
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

