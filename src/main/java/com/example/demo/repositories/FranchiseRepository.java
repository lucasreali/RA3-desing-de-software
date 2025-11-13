
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.persistence.*;

public class FranchiseRepository {
    EntityManagerFactory emFactory;
    EntityManager entityManager;

    public FranchiseRepository() {
        emFactory =
                Persistence.createEntityManagerFactory("franchiseEmFactory");
        entityManager = emFactory.createEntityManager();
    }

    public void save(Franchise franchise) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.persist(franchise);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void edit(String id, String localization) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Franchise franchise = findFranchiseById(id);
            franchise.setLocalization(localization);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Franchise findFranchiseById(String id) {
        Query query =
                entityManager.createQuery("select f from Franchise f where f" +
                        ".id" +
                        " = :id");
        query.setParameter("id", id);
        return (Franchise) query.getSingleResult();
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Franchise franchise = findFranchiseById(id);
            entityManager.remove(franchise);
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

