package com.example.demo.dtos.car;

import com.example.demo.models.Car;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDTO {

    @NotNull
    @NotBlank
    private String brand;

    @NotNull
    @NotBlank
    private String model;

    @NotNull
    @NotBlank
    private String category;

    @NotNull
    @NotBlank
    private String color;

    @NotNull
    private int price;

    @NotNull
    @NotBlank
    private String year;


    public Car convertToEntity() {
        Car car = new Car();

        car.setBrand(brand);
        car.setModel(model);
        car.setCategory(category);
        car.setColor(color);
        car.setPrice(price);
        car.setYear(year);

        return car;
    }
}
