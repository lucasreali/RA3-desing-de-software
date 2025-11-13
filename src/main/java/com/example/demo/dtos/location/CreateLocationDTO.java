package com.example.demo.dtos.location;

import com.example.demo.models.Location;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationDTO {
    @NotNull
    private int value;

    @NotNull
    private LocalDateTime expiration;

    @NotNull
    private UUID customerId;

    @NotNull
    private UUID carId;

    @NotNull
    private UUID paymentMethodId;

    public Location convertToEntity() {
        Location location = new Location();
        location.setValue(value);
        location.setExpiration(expiration);
        return location;
    }
}


