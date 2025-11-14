package com.example.demo.dtos.location;

import com.example.demo.dtos.car.ResponseCarDTO;
import com.example.demo.dtos.customer.ResponseCustomerDTO;
import com.example.demo.dtos.paymentmethod.ResponsePaymentMethodDTO;
import com.example.demo.models.Location;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponseLocationDTO {
    private final UUID id;
    private final int value;
    private final LocalDateTime expiration;
    private final ResponseCustomerDTO customer;
    private final ResponseCarDTO car;
    private final ResponsePaymentMethodDTO paymentMethod;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ResponseLocationDTO(Location location) {
        this.id = location.getId();
        this.value = location.getValue();
        this.expiration = location.getExpiration();
        this.customer = location.getCustomer() != null
            ? new ResponseCustomerDTO(location.getCustomer())
            : null;
        this.car = location.getCar() != null
            ? new ResponseCarDTO(location.getCar())
            : null;
        this.paymentMethod = location.getPaymentMethod() != null
            ? new ResponsePaymentMethodDTO(location.getPaymentMethod())
            : null;
        this.createdAt = location.getCreatedAt();
        this.updatedAt = location.getUpdatedAt();
    }
}

