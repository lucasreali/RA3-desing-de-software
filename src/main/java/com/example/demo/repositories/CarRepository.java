package com.example.demo.repositories;


import com.example.demo.models.Car;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CarRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Car car) {
        entityManager.persist(car);
    }

    public Car edit(Car car) {
        Car updateDcar = findCarById(car.getId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + car.getId()));

        if (car.getBrand() != null) {
            updateDcar.setBrand(car.getBrand());
        }
        if (car.getModel() != null) {
            updateDcar.setModel(car.getModel());
        }
        if (car.getCategory() != null) {
            updateDcar.setCategory(car.getCategory());
        }
        if (car.getColor() != null) {
            updateDcar.setColor(car.getColor());
        }
        if (car.getPrice() != 0) {
            updateDcar.setPrice(car.getPrice());
        }
        if (car.getYear() != null) {
            updateDcar.setYear(car.getYear());
        }

        return updateDcar;
    }

    public Optional<Car> findCarById(UUID id) {
        try {
            Query query =
                    entityManager.createQuery("select c from Car c where c.id = :id");
            query.setParameter("id", id);
            return Optional.of((Car) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void delete(UUID id) {
        Car car = findCarById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
        entityManager.remove(car);
    }
}
