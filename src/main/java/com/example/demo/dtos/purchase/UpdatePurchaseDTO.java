package com.example.demo.dtos.purchase;

import com.example.demo.models.Purchase;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePurchaseDTO {

    private int installment;
    private UUID customerId;
    private UUID carId;
    private UUID paymentMethodId;

    public Purchase convertToEntity() {
        Purchase purchase = new Purchase();

        purchase.setInstallment(installment);

        return purchase;
    }
}
