package com.example.demo.repositories;

import com.example.demo.models.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class LocationRepository {

    private final EntityManagerFactory emf;

    public LocationRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Location location) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();
            em.persist(location);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public Location edit(Location location) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            Location updatedLocation = em.find(Location.class, location.getId());

            if (updatedLocation == null) {
                throw new EntityNotFoundException("Location not found with id: " + location.getId());
            }

            if (location.getCar() != null) updatedLocation.setCar(location.getCar());
            if (location.getCustomer() != null) updatedLocation.setCustomer(location.getCustomer());
            if (location.getPaymentMethod() != null) updatedLocation.setPaymentMethod(location.getPaymentMethod());
            if (location.getValue() != 0) updatedLocation.setValue(location.getValue());
            if (location.getExpiration() != null) updatedLocation.setExpiration(location.getExpiration());

            tx.commit();
            return updatedLocation;

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }

    public Optional<Location> findLocationById(UUID id) {

        try (EntityManager em = emf.createEntityManager()) {
            Location location = em.find(Location.class, id);
            return Optional.ofNullable(location);
        }
    }

    public void delete(UUID id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            Location location = em.find(Location.class, id);

            if (location == null) {
                throw new EntityNotFoundException("Location not found with id: " + id);
            }

            em.remove(location);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }
}
