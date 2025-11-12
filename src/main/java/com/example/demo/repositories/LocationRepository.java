
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.UUID;

public class LocationRepository {
    EntityManager entityManager;
    EntityTransaction transaction;

    public LocationRepository(){
        EntityManagerFactory emFactory =
                Persistence.createEntityManagerFactory("locationEmFactory");
        entityManager = emFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    public void save(Location location) {
        try {
            transaction.begin();

            entityManager.persist(location);

            entityManager.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (RuntimeException nestedExcetion) {
                }
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
}

