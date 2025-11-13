
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class LocationRepository {
    private final EntityManager entityManager;

    @Autowired
    public LocationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Location location) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.persist(location);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void edit(String id, Car car, int value, LocalDateTime expiration) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Location location = findLocationById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + id));
            location.setCar(car);
            location.setValue(value);
            location.setExpiration(expiration);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Optional<Location> findLocationById(String id) {
        try {
            Query query =
                    entityManager.createQuery("select l from Location l where l" +
                            ".id" +
                            " = :id");
            query.setParameter("id", id);
            return Optional.of((Location) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Location location = findLocationById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + id));
            entityManager.remove(location);
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

