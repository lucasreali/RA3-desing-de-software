package com.example.demo.dtos.car;

import com.example.demo.models.Car;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarDTO {
    private String brand;

    private String model;

    private String category;

    private String color;

    private int price;

    private String year;

    public Car convertToEntity() {
        Car car = new Car();

        car.setBrand(brand != null && !brand.isBlank() ? brand : null);
        car.setModel(model != null && !model.isBlank() ? model : null);
        car.setCategory(
                category != null && !category.isBlank() ? category : null);
        car.setColor(color != null && !color.isBlank() ? color : null);
        car.setPrice(price <= 0 ? price : 0);
        car.setYear(year != null && !year.isBlank() ? year : null);

        return car;
    }
}
