package com.example.demo.controllers;

import com.example.demo.dtos.car.CreateCarDTO;
import com.example.demo.dtos.car.ResponseCarDTO;
import com.example.demo.dtos.car.UpdateCarDTO;
import com.example.demo.services.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<ResponseCarDTO> create(@Valid @RequestBody
                                                      CreateCarDTO createCarDTO) {
        ResponseCarDTO responseCarDTO =
                carService.create(createCarDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCarDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> findById(@PathVariable UUID id) {
        ResponseCarDTO responseCarDTO = carService.findById(id);
        return ResponseEntity.ok(responseCarDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> update(@PathVariable UUID id,
                                               @Valid @RequestBody
    UpdateCarDTO updateCarDTO) {
        ResponseCarDTO responseCarDTO = carService.update(id,
                updateCarDTO);
        return ResponseEntity.ok(responseCarDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
