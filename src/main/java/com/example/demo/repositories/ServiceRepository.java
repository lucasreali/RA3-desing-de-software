
package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.persistence.*;


public class ServiceRepository {
    EntityManagerFactory emFactory;
    EntityManager entityManager;

    public ServiceRepository() {
        emFactory = Persistence.createEntityManagerFactory("serviceEmFactory");
        entityManager = emFactory.createEntityManager();
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

            Service service = findServiceById(id);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Service findServiceById(String id) {
        Query query =
                entityManager.createQuery("select s from Service s where s" +
                        ".id" +
                        " = :id");
        query.setParameter("id", id);
        return (Service) query.getSingleResult();
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Service service = findServiceById(id);
            entityManager.remove(service);
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
