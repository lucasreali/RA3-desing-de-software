package com.example.demo.repositories;


import com.example.demo.models.Car;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CarRepository {
    private final EntityManager entityManager;

    @Autowired
    public CarRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Car car) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.persist(car);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void edit(String id, String brand, String model, String category,
                     String color, int price, String year) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Car car = findCarById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
            car.setBrand(brand);
            car.setModel(model);
            car.setCategory(category);
            car.setColor(color);
            car.setPrice(price);
            car.setYear(year);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Optional<Car> findCarById(String id) {
        try {
            Query query =
                    entityManager.createQuery("select c from Car c where c.id" +
                            " = :id");
            query.setParameter("id", id);
            return Optional.of((Car) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Car car = findCarById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
            entityManager.remove(car);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    @PreDestroy
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
