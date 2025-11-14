package com.example.demo.dtos.service;

import com.example.demo.models.Service;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateServiceDTO {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Integer price;

    @NotNull
    private UUID carId;

    public Service convertToEntity() {
        Service service = new Service();
        service.setName(name);
        service.setDescription(description);
        service.setPrice(price);
        return service;
    }
}