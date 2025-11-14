package com.example.demo.repositories;

import com.example.demo.models.Franchise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class FranchiseRepository {

    private final EntityManagerFactory emf;

    public FranchiseRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Franchise franchise) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();
            em.persist(franchise);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public Franchise edit(Franchise franchise) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            Franchise updatedFranchise = em.find(Franchise.class, franchise.getId());

            if (updatedFranchise == null) {
                throw new EntityNotFoundException("Franchise not found with id: " + franchise.getId());
            }

            if (franchise.getLocalization() != null) updatedFranchise.setLocalization(franchise.getLocalization());

            tx.commit();
            return updatedFranchise;

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }

    public Optional<Franchise> findFranchiseById(UUID id) {

        try (EntityManager em = emf.createEntityManager()) {
            Franchise franchise = em.find(Franchise.class, id);
            return Optional.ofNullable(franchise);
        }
    }

    public void delete(UUID id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            Franchise franchise = em.find(Franchise.class, id);

            if (franchise == null) {
                throw new EntityNotFoundException("Franchise not found with id: " + id);
            }

            em.remove(franchise);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }
}
