package com.example.demo.dtos.purchase;

import com.example.demo.models.Car;
import com.example.demo.models.Customer;
import com.example.demo.models.PaymentMethod;
import com.example.demo.models.Purchase;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePurchaseDTO {

    @NotNull
    @NotBlank
    private int installment;

    @NotNull
    @NotBlank
    private UUID customerId;

    @NotNull
    @NotBlank
    private UUID carId;

    @NotNull
    @NotBlank
    private UUID paymentMethodId;

    public Purchase convertToEntity(){
        Purchase purchase = new Purchase();

        purchase.setInstallment(installment);
        return purchase;
    }
}
