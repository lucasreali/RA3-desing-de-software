package com.example.demo.repositories;


import com.example.demo.models.Car;
import jakarta.persistence.*;


public class CarRepository {
    EntityManagerFactory emFactory;
    EntityManager entityManager;

    public CarRepository() {
        emFactory = Persistence.createEntityManagerFactory("carEmFactory");
        entityManager = emFactory.createEntityManager();
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

            Car car = findCarById(id);
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

    public Car findCarById(String id) {
        Query query =
                entityManager.createQuery("select c from Car c where c.id" +
                        " = :id");
        query.setParameter("id", id);
        return (Car) query.getSingleResult();
    }

    public void delete(String id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Car car = findCarById(id);
            entityManager.remove(car);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void close() {
        if (entityManager.isOpen()) entityManager.close();
        if (emFactory.isOpen()) emFactory.close();
    }
}
