package com.example.demo.repositories;


import com.example.demo.models.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentMethodRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(PaymentMethod paymentMethod) {
        entityManager.persist(paymentMethod);
    }

    @Transactional
    public PaymentMethod edit(PaymentMethod paymentMethod) {
        PaymentMethod updateDpaymentMethod = findPaymentMethodById(paymentMethod.getId())
                .orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found with id: " + paymentMethod.getId()));

        if (paymentMethod.getName() != null) {
            updateDpaymentMethod.setName(paymentMethod.getName());
        }
        if (paymentMethod.getDescription() != null) {
            updateDpaymentMethod.setDescription(paymentMethod.getDescription());
        }

        return updateDpaymentMethod;
    }

    public Optional<PaymentMethod> findPaymentMethodById(UUID id) {
        try {
            Query query =
                    entityManager.createQuery("select p from PaymentMethod p where p.id = :id");
            query.setParameter("id", id);
            return Optional.of((PaymentMethod) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public void delete(UUID id) {
        PaymentMethod paymentMethod = findPaymentMethodById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found with id: " + id));
        entityManager.remove(paymentMethod);
    }
}
