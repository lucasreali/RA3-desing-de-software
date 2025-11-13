package com.example.demo.dtos.location;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UpdateLocationDTO {
    private Integer value;
    private LocalDateTime expiration;
    private UUID customerId;
    private UUID carId;
    private UUID paymentMethodId;
}

