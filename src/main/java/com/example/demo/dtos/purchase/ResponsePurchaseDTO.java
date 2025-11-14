package com.example.demo.dtos.purchase;

import com.example.demo.dtos.car.ResponseCarDTO;
import com.example.demo.dtos.customer.ResponseCustomerDTO;
import com.example.demo.dtos.paymentmethod.ResponsePaymentMethodDTO;
import com.example.demo.models.Location;
import com.example.demo.models.Purchase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponsePurchaseDTO {

    private final UUID id;
    private final int installment;
    private final ResponseCustomerDTO customer;
    private final ResponseCarDTO car;
    private final ResponsePaymentMethodDTO paymentMethod;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ResponsePurchaseDTO(Purchase purchase) {
        this.id = purchase.getId();
        this.installment = purchase.getInstallment();
        this.customer = purchase.getCustomer() != null
                ? new ResponseCustomerDTO(purchase.getCustomer())
                : null;
        this.car = purchase.getCar() != null
                ? new ResponseCarDTO(purchase.getCar())
                : null;
        this.paymentMethod = purchase.getPaymentMethod() != null
                ? new ResponsePaymentMethodDTO(purchase.getPaymentMethod())
                : null;
        this.createdAt = purchase.getCreatedAt();
        this.updatedAt = purchase.getUpdatedAt();
    }
}
