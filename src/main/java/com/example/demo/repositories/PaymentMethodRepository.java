package com.example.demo.repositories;

import com.example.demo.models.PaymentMethod;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentMethodRepository {

    private final EntityManagerFactory emf;

    public PaymentMethodRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(PaymentMethod paymentMethod) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();
            em.persist(paymentMethod);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public PaymentMethod edit(PaymentMethod paymentMethod) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            PaymentMethod updatedPaymentMethod = em.find(PaymentMethod.class, paymentMethod.getId());

            if (updatedPaymentMethod == null) {
                throw new EntityNotFoundException("PaymentMethod not found with id: " + paymentMethod.getId());
            }

            if (paymentMethod.getName() != null) updatedPaymentMethod.setName(paymentMethod.getName());
            if (paymentMethod.getDescription() != null) updatedPaymentMethod.setDescription(paymentMethod.getDescription());

            tx.commit();
            return updatedPaymentMethod;

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }

    public Optional<PaymentMethod> findPaymentMethodById(UUID id) {

        try (EntityManager em = emf.createEntityManager()) {
            PaymentMethod paymentMethod = em.find(PaymentMethod.class, id);
            return Optional.ofNullable(paymentMethod);
        }
    }

    public void delete(UUID id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();

            PaymentMethod paymentMethod = em.find(PaymentMethod.class, id);

            if (paymentMethod == null) {
                throw new EntityNotFoundException("PaymentMethod not found with id: " + id);
            }

            em.remove(paymentMethod);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        }
    }
}
