package com.example.demo.controllers;

import com.example.demo.dtos.franchise.CreateFranchiseDTO;
import com.example.demo.dtos.franchise.ResponseFranchiseDTO;
import com.example.demo.dtos.franchise.UpdateFranchiseDTO;
import com.example.demo.services.FranchiseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/franchise")
public class FranchiseController {
    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @PostMapping
    public ResponseEntity<ResponseFranchiseDTO> create(@Valid @RequestBody CreateFranchiseDTO createFranchiseDTO) {
        ResponseFranchiseDTO responseFranchiseDTO = franchiseService.create(createFranchiseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseFranchiseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFranchiseDTO> findById(@PathVariable UUID id) {
        ResponseFranchiseDTO responseFranchiseDTO = franchiseService.findById(id);
        return ResponseEntity.ok(responseFranchiseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseFranchiseDTO> update(@PathVariable UUID id, @Valid @RequestBody UpdateFranchiseDTO updateFranchiseDTO) {
        ResponseFranchiseDTO responseFranchiseDTO = franchiseService.update(id, updateFranchiseDTO);
        return ResponseEntity.ok(responseFranchiseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        franchiseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

