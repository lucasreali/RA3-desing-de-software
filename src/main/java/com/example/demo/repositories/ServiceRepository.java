
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ServiceRepository {
    private final EntityManager entityManager;

    @Autowired
    public ServiceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Service service) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.persist(service);

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

            Service service = findServiceById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Optional<Service> findServiceById(String id) {
        try {
            Query query =
                    entityManager.createQuery("select s from Service s where s" +
                            ".id" +
                            " = :id");
            query.setParameter("id", id);
            return Optional.of((Service) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Service service = findServiceById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));
            entityManager.remove(service);
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
