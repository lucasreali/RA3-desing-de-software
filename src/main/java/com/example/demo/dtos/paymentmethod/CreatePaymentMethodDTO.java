package com.example.demo.dtos.paymentmethod;

import com.example.demo.models.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentMethodDTO {

    @NotNull
    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    public PaymentMethod convertToEntity() {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName(this.name);
        paymentMethod.setDescription(this.description);
        return paymentMethod;
    }
}

