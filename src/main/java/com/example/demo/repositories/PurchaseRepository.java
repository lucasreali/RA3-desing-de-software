package com.example.demo.repositories;

import com.example.demo.models.Purchase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PurchaseRepository {

    private final EntityManagerFactory emf;

    public PurchaseRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Purchase purchase) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();
            em.persist(purchase);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public Purchase edit(Purchase purchase) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            Purchase updatedPurchase = em.find(Purchase.class, purchase.getId());

            if (updatedPurchase == null) {
                throw new EntityNotFoundException("Purchase not found with id: " + purchase.getId());
            }

            if (purchase.getInstallment() != 0) updatedPurchase.setInstallment(purchase.getInstallment());

            tx.commit();
            return updatedPurchase;

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }

    public Optional<Purchase> findPurchaseById(UUID id) {

        try (EntityManager em = emf.createEntityManager()) {
            Purchase purchase = em.find(Purchase.class, id);
            return Optional.ofNullable(purchase);
        }
    }

    public void delete(UUID id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            Purchase purchase = em.find(Purchase.class, id);

            if (purchase == null) {
                throw new EntityNotFoundException("Purchase not found with id: " + id);
            }

            em.remove(purchase);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }
}
