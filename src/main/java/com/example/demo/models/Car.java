package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String brand;

    private String model;

    private String category;

    private String color;

    private int price;

    private String year;

    @OneToMany(mappedBy = "car")
    private List<Location> locations;

    @OneToMany(mappedBy = "car")
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "car")
    private List<Service> services;

}
