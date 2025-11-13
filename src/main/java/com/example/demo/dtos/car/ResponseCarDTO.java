package com.example.demo.dtos.car;

import com.example.demo.models.Car;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO de resposta para Car
 */
@Getter
@Setter
public class ResponseCarDTO {
    private final UUID id;
    private final String brand;
    private final String model;
    private final String category;
    private final String color;
    private final int price;
    private final String year;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ResponseCarDTO(Car car) {
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.category = car.getCategory();
        this.color = car.getColor();
        this.price = car.getPrice();
        this.year = car.getYear();
        this.createdAt = car.getCreatedAt();
        this.updatedAt = car.getUpdatedAt();
    }
}

