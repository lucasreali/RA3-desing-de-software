package com.example.demo.services;

import com.example.demo.dtos.paymentmethod.CreatePaymentMethodDTO;
import com.example.demo.dtos.paymentmethod.ResponsePaymentMethodDTO;
import com.example.demo.dtos.paymentmethod.UpdatePaymentMethodDTO;
import com.example.demo.models.PaymentMethod;
import com.example.demo.repositories.PaymentMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    public ResponsePaymentMethodDTO create(CreatePaymentMethodDTO createPaymentMethodDTO) {
        PaymentMethod paymentMethod = createPaymentMethodDTO.convertToEntity();
        paymentMethodRepository.save(paymentMethod);
        return new ResponsePaymentMethodDTO(paymentMethod);
    }

    public ResponsePaymentMethodDTO findById(UUID id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findPaymentMethodById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found with id: " + id));
        return new ResponsePaymentMethodDTO(paymentMethod);
    }

    public ResponsePaymentMethodDTO update(UUID id, UpdatePaymentMethodDTO updatePaymentMethodDTO) {
        PaymentMethod paymentMethod = updatePaymentMethodDTO.convertToEntity();
        paymentMethod.setId(id);
        PaymentMethod updatedPaymentMethod = paymentMethodRepository.edit(paymentMethod);
        return new ResponsePaymentMethodDTO(updatedPaymentMethod);
    }

    public void delete(UUID id) {
        paymentMethodRepository.delete(id);
    }
}
