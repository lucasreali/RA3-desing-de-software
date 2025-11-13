package com.example.demo.repositories;


import com.example.demo.models.Location;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public class LocationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Location location) {
        entityManager.persist(location);
    }

    @Transactional
    public Location edit(Location location) {
        Location updateDlocation = findLocationById(location.getId()).orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + location.getId()));

        if (location.getCar() != null) {
            updateDlocation.setCar(location.getCar());
        }
        if (location.getCustomer() != null) {
            updateDlocation.setCustomer(location.getCustomer());
        }
        if (location.getPaymentMethod() != null) {
            updateDlocation.setPaymentMethod(location.getPaymentMethod());
        }
        if (location.getValue() != 0) {
            updateDlocation.setValue(location.getValue());
        }
        if (location.getExpiration() != null) {
            updateDlocation.setExpiration(location.getExpiration());
        }

        return updateDlocation;
    }

    public Optional<Location> findLocationById(UUID id) {
        try {
            Query query = entityManager.createQuery("select l from Location l where l.id = :id");
            query.setParameter("id", id);
            return Optional.of((Location) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public void delete(UUID id) {
        Location location = findLocationById(id).orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + id));
        entityManager.remove(location);
    }
}
