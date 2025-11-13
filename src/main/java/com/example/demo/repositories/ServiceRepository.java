package com.example.demo.repositories;


import com.example.demo.models.Service;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ServiceRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Service service) {
        entityManager.persist(service);
    }

    @Transactional
    public Service edit(Service service) {
        Service updateDservice = findServiceById(service.getId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + service.getId()));

        if (service.getName() != null) {
            updateDservice.setName(service.getName());
        }
        if (service.getDescription() != null) {
            updateDservice.setDescription(service.getDescription());
        }
        if (service.getPrice() != 0) {
            updateDservice.setPrice(service.getPrice());
        }
        if (service.getCar() != null) {
            updateDservice.setCar(service.getCar());
        }

        return updateDservice;
    }

    public Optional<Service> findServiceById(UUID id) {
        try {
            Query query =
                    entityManager.createQuery("select s from Service s where s.id = :id");
            query.setParameter("id", id);
            return Optional.of((Service) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public void delete(UUID id) {
        Service service = findServiceById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));
        entityManager.remove(service);
    }
}
