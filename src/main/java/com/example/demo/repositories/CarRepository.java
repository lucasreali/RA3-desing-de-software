package com.example.demo.repositories;

import com.example.demo.models.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CarRepository {

    private final EntityManagerFactory emf;

    public CarRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Car car) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(car);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Car edit(Car car) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Car updatedCar = em.find(Car.class, car.getId());
            if (updatedCar == null) {
                throw new EntityNotFoundException("Car not found with id: " + car.getId());
            }
            if (car.getBrand() != null) updatedCar.setBrand(car.getBrand());
            if (car.getModel() != null) updatedCar.setModel(car.getModel());
            if (car.getCategory() != null) updatedCar.setCategory(car.getCategory());
            if (car.getColor() != null) updatedCar.setColor(car.getColor());
            if (car.getPrice() != 0) updatedCar.setPrice(car.getPrice());
            if (car.getYear() != null) updatedCar.setYear(car.getYear());
            tx.commit();
            return updatedCar;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Optional<Car> findCarById(UUID id) {
        EntityManager em = emf.createEntityManager();
        try {
            Car car = em.find(Car.class, id);
            return Optional.ofNullable(car);
        } finally {
            em.close();
        }
    }

    public void delete(UUID id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Car car = em.find(Car.class, id);
            if (car == null) {
                throw new EntityNotFoundException("Car not found with id: " + id);
            }
            em.remove(car);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
