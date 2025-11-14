package com.example.demo.repositories;

import com.example.demo.models.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ServiceRepository {

    private final EntityManagerFactory emf;

    public ServiceRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Service service) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();
            em.persist(service);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public Service edit(Service service) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            Service updatedService = em.find(Service.class, service.getId());

            if (updatedService == null) {
                throw new EntityNotFoundException("Service not found with id: " + service.getId());
            }

            if (service.getName() != null) updatedService.setName(service.getName());
            if (service.getDescription() != null) updatedService.setDescription(service.getDescription());
            if (service.getPrice() != 0) updatedService.setPrice(service.getPrice());
            if (service.getCar() != null) updatedService.setCar(service.getCar());

            tx.commit();
            return updatedService;

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }

    public Optional<Service> findServiceById(UUID id) {

        try (EntityManager em = emf.createEntityManager()) {
            Service service = em.find(Service.class, id);
            return Optional.ofNullable(service);
        }
    }

    public void delete(UUID id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            Service service = em.find(Service.class, id);

            if (service == null) {
                throw new EntityNotFoundException("Service not found with id: " + id);
            }

            em.remove(service);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }
}
