package com.example.demo.controllers;

import com.example.demo.dtos.paymentmethod.CreatePaymentMethodDTO;
import com.example.demo.dtos.paymentmethod.ResponsePaymentMethodDTO;
import com.example.demo.dtos.paymentmethod.UpdatePaymentMethodDTO;
import com.example.demo.services.PaymentMethodService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/paymentMethod")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    public ResponseEntity<ResponsePaymentMethodDTO> create(@Valid @RequestBody CreatePaymentMethodDTO createPaymentMethodDTO) {
        ResponsePaymentMethodDTO responsePaymentMethodDTO = paymentMethodService.create(createPaymentMethodDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePaymentMethodDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePaymentMethodDTO> findById(@PathVariable UUID id) {
        ResponsePaymentMethodDTO responsePaymentMethodDTO = paymentMethodService.findById(id);
        return ResponseEntity.ok(responsePaymentMethodDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePaymentMethodDTO> update(@PathVariable UUID id, @Valid @RequestBody UpdatePaymentMethodDTO updatePaymentMethodDTO) {
        ResponsePaymentMethodDTO responsePaymentMethodDTO = paymentMethodService.update(id, updatePaymentMethodDTO);
        return ResponseEntity.ok(responsePaymentMethodDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        paymentMethodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

