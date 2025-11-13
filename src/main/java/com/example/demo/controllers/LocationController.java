package com.example.demo.controllers;

import com.example.demo.dtos.location.CreateLocationDTO;
import com.example.demo.dtos.location.ResponseLocationDTO;
import com.example.demo.dtos.location.UpdateLocationDTO;
import com.example.demo.services.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<ResponseLocationDTO> create(@Valid @RequestBody CreateLocationDTO createLocationDTO) {
        ResponseLocationDTO responseLocationDTO = locationService.create(createLocationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseLocationDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseLocationDTO> findById(@PathVariable UUID id) {
        ResponseLocationDTO responseLocationDTO = locationService.findById(id);
        return ResponseEntity.ok(responseLocationDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseLocationDTO> update(@PathVariable UUID id, @Valid @RequestBody UpdateLocationDTO updateLocationDTO) {
        ResponseLocationDTO responseLocationDTO = locationService.update(id, updateLocationDTO);
        return ResponseEntity.ok(responseLocationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        locationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
