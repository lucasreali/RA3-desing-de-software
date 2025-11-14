package com.example.demo.services;

import com.example.demo.dtos.car.CreateCarDTO;
import com.example.demo.dtos.car.ResponseCarDTO;
import com.example.demo.dtos.car.UpdateCarDTO;

import com.example.demo.models.Car;

import com.example.demo.repositories.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public ResponseCarDTO create(CreateCarDTO createCarDTO) {
        Car car = createCarDTO.convertToEntity();
        carRepository.save(car);
        return new ResponseCarDTO(car);
    }

    public ResponseCarDTO findById(UUID id) {
        Car car = carRepository.findCarById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found" +
                        " with id: " + id));
        return new ResponseCarDTO(car);
    }

    public ResponseCarDTO update(String id,
                                UpdateCarDTO updateCarDTO) {
        Car car =
                carRepository.edit(updateCarDTO.convertToEntity());
        return new ResponseCarDTO(car);
    }

    public void delete(UUID id) {
        carRepository.delete(id);
    }

}
