package com.example.demo.repositories;


import com.example.demo.models.Purchase;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PurchaseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Purchase purchase) {
        entityManager.persist(purchase);
    }

    public Purchase edit(Purchase purchase) {
        Purchase updateDpurchase = findPurchaseById(purchase.getId())
                .orElseThrow(() -> new EntityNotFoundException("Purchase not found with id: " + purchase.getId()));

        if (purchase.getInstallment() != 0) {
            updateDpurchase.setInstallment(purchase.getInstallment());
        }

        return updateDpurchase;
    }

    public Optional<Purchase> findPurchaseById(UUID id) {
        try {
            Query query =
                    entityManager.createQuery("select p from Purchase p where p.id = :id");
            query.setParameter("id", id);
            return Optional.of((Purchase) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void delete(UUID id) {
        Purchase purchase = findPurchaseById(id)
                .orElseThrow(() -> new EntityNotFoundException("Purchase not found with id: " + id));
        entityManager.remove(purchase);
    }
}
