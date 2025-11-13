
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class LocationRepository {
    EntityManagerFactory emFactory;
    EntityManager entityManager;

    public LocationRepository() {
        emFactory = Persistence.createEntityManagerFactory("locationEmFactory");
        entityManager = emFactory.createEntityManager();
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

            Location location = findLocationById(id);
            location.setCar(car);
            location.setValue(value);
            location.setExpiration(expiration);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Location findLocationById(String id) {
        Query query =
                entityManager.createQuery("select l from Location l where l" +
                        ".id" +
                        " = :id");
        query.setParameter("id", id);
        return (Location) query.getSingleResult();
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Location location = findLocationById(id);
            entityManager.remove(location);
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

