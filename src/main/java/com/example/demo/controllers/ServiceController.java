package com.example.demo.controllers;

import com.example.demo.services.ServiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/service")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<ResponseServiceDTO> create(@Valid @RequestBody CreateServiceDTO createServiceDTO) {
        ResponseServiceDTO responseServiceDTO = serviceService.create(createServiceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseServiceDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseServiceDTO> findById(@PathVariable UUID id) {
        ResponseServiceDTO responseServiceDTO = serviceService.findById(id);
        return ResponseEntity.ok(responseServiceDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseServiceDTO> update(@PathVariable UUID id, @Valid @RequestBody UpdateServiceDTO updateServiceDTO) {
        ResponseServiceDTO responseServiceDTO = serviceService.update(id, updateServiceDTO);
        return ResponseEntity.ok(responseServiceDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        serviceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}