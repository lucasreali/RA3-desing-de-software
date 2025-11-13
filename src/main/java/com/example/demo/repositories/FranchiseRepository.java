
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FranchiseRepository {
    private final EntityManager entityManager;

    @Autowired
    public FranchiseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
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

            Franchise franchise = findFranchiseById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Franchise not found with id: " + id));
            franchise.setLocalization(localization);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Optional<Franchise> findFranchiseById(String id) {
        try {
            Query query =
                    entityManager.createQuery("select f from Franchise f where f" +
                            ".id" +
                            " = :id");
            query.setParameter("id", id);
            return Optional.of((Franchise) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Franchise franchise = findFranchiseById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Franchise not found with id: " + id));
            entityManager.remove(franchise);
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

