package com.example.demo.services;


import com.example.demo.dtos.purchase.CreatePurchaseDTO;
import com.example.demo.dtos.purchase.ResponsePurchaseDTO;
import com.example.demo.dtos.purchase.UpdatePurchaseDTO;
import com.example.demo.models.Car;
import com.example.demo.models.Customer;
import com.example.demo.models.PaymentMethod;
import com.example.demo.models.Purchase;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.PaymentMethodRepository;
import com.example.demo.repositories.PurchaseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public ResponsePurchaseDTO create(CreatePurchaseDTO createPurchaseDTO) {
        Customer customer =
                customerRepository.findCustomerById(createPurchaseDTO.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + createPurchaseDTO.getCustomerId()));
        Car car =
                carRepository.findCarById(createPurchaseDTO.getCarId()).orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + createPurchaseDTO.getCarId()));
        PaymentMethod paymentMethod =
                paymentMethodRepository.findPaymentMethodById(createPurchaseDTO.getPaymentMethodId()).orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found with id: " + createPurchaseDTO.getPaymentMethodId()));

        Purchase purchase = createPurchaseDTO.convertToEntity();
        purchase.setCustomer(customer);
        purchase.setCar(car);
        purchase.setPaymentMethod(paymentMethod);

        purchaseRepository.save(purchase);
        return new ResponsePurchaseDTO(purchase);

    }

    public ResponsePurchaseDTO findById(UUID id) {
        Purchase purchase = purchaseRepository.findPurchaseById(id)
                .orElseThrow(() -> new EntityNotFoundException("Purchase not " +
                        "found" +
                        " with id: " + id));
        return new ResponsePurchaseDTO(purchase);
    }

    public ResponsePurchaseDTO update(UUID id,
                                 UpdatePurchaseDTO updatePurchaseDTO) {
        Purchase purchase = new Purchase();
        purchase.setId(id);

        if (updatePurchaseDTO.getInstallment() != 0){
            purchase.setInstallment(updatePurchaseDTO.getInstallment());
        }

        if (updatePurchaseDTO.getCustomerId() != null){
            Customer customer =
                    customerRepository.findCustomerById(updatePurchaseDTO.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + updatePurchaseDTO.getCustomerId()));
            purchase.setCustomer(customer);
        }

        if (updatePurchaseDTO.getCarId() != null){
            Car car =
                    carRepository.findCarById(updatePurchaseDTO.getCarId()).orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + updatePurchaseDTO.getCarId()));
            purchase.setCar(car);
        }

        if (updatePurchaseDTO.getPaymentMethodId() != null){
            PaymentMethod paymentMethod =
                    paymentMethodRepository.findPaymentMethodById(updatePurchaseDTO.getPaymentMethodId()).orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found with id: " + updatePurchaseDTO.getPaymentMethodId()));
            purchase.setPaymentMethod(paymentMethod);
        }

        Purchase updatedPurchase = purchaseRepository.edit(purchase);
        return new ResponsePurchaseDTO(updatedPurchase);
    }

    public void delete(UUID id) {
        purchaseRepository.delete(id);
    }
}
