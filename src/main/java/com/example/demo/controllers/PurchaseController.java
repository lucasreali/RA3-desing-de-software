package com.example.demo.controllers;

import com.example.demo.dtos.purchase.CreatePurchaseDTO;
import com.example.demo.dtos.purchase.ResponsePurchaseDTO;
import com.example.demo.dtos.purchase.UpdatePurchaseDTO;
import com.example.demo.services.PurchaseService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<ResponsePurchaseDTO> create(@Valid @RequestBody
                                                      CreatePurchaseDTO createPurchaseDTO){
        ResponsePurchaseDTO responsePurchaseDTO =
                purchaseService.create(createPurchaseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePurchaseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePurchaseDTO> findById(@PathVariable UUID id){
        ResponsePurchaseDTO responsePurchaseDTO = purchaseService.findById(id);
        return ResponseEntity.ok(responsePurchaseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePurchaseDTO> update(@PathVariable UUID id,
                                                      @Valid @RequestBody UpdatePurchaseDTO updatePurchaseDTO){
        ResponsePurchaseDTO responsePurchaseDTO = purchaseService.update(id,
                updatePurchaseDTO);
        return ResponseEntity.ok(responsePurchaseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        purchaseService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
