package com.example.demo.dtos.paymentmethod;

import com.example.demo.models.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
public class ResponsePaymentMethodDTO {
    private final UUID id;
    private final String name;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ResponsePaymentMethodDTO(PaymentMethod paymentMethod) {
        this.id = paymentMethod.getId();
        this.name = paymentMethod.getName();
        this.description = paymentMethod.getDescription();
        this.createdAt = paymentMethod.getCreatedAt();
        this.updatedAt = paymentMethod.getUpdatedAt();
    }
}

