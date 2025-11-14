package com.example.demo.dtos.service;

import com.example.demo.models.Service;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponseServiceDTO {
    private final UUID id;
    private final String name;
    private final String description;
    private final int price;
    private final UUID carId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ResponseServiceDTO(Service service) {
        id = service.getId();
        name = service.getName();
        description = service.getDescription();
        price = service.getPrice();
        carId = service.getCar() != null ? service.getCar().getId() : null;
        createdAt = service.getCreatedAt();
        updatedAt = service.getUpdatedAt();
    }
}
