package com.example.demo.dtos.service;

import com.example.demo.models.Service;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateServiceDTO {
    private String name;
    private String description;
    @Positive
    private Integer price;
    private UUID carId;

    public Service convertToEntity() {
        Service service = new Service();
        service.setName(name != null && !name.isBlank() ? name : null);
        service.setDescription(description != null && !description.isBlank() ? description : null);
        service.setPrice(price != null ? price : 0);
        return service;
    }
}