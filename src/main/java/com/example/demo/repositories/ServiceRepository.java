
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

public class ServiceRepository {
    EntityManager entityManager;
    EntityTransaction transaction;

    public ServiceRepository(){
        EntityManagerFactory emFactory =
                Persistence.createEntityManagerFactory("serviceEmFactory");
        entityManager = emFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    public void save(Service service) {
        try {
            transaction.begin();

            entityManager.persist(service);

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
