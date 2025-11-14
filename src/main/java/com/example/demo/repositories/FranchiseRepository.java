package com.example.demo.repositories;


import com.example.demo.models.Franchise;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class FranchiseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Franchise franchise) {
        entityManager.persist(franchise);
    }

    public Franchise edit(Franchise franchise) {
        Franchise updateDfranchise = findFranchiseById(franchise.getId())
                .orElseThrow(() -> new EntityNotFoundException("Franchise not found with id: " + franchise.getId()));

        if (franchise.getLocalization() != null) {
            updateDfranchise.setLocalization(franchise.getLocalization());
        }

        return updateDfranchise;
    }

    public Optional<Franchise> findFranchiseById(UUID id) {
        try {
            Query query =
                    entityManager.createQuery("select f from Franchise f where f.id = :id");
            query.setParameter("id", id);
            return Optional.of((Franchise) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void delete(UUID id) {
        Franchise franchise = findFranchiseById(id)
                .orElseThrow(() -> new EntityNotFoundException("Franchise not found with id: " + id));
        entityManager.remove(franchise);
    }
}
