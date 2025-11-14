package com.example.demo.services;

import com.example.demo.dtos.service.CreateServiceDTO;
import com.example.demo.dtos.service.ResponseServiceDTO;
import com.example.demo.dtos.service.UpdateServiceDTO;
import com.example.demo.models.Car;
import com.example.demo.models.Service;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import java.util.UUID;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final CarRepository carRepository;

    public ResponseServiceDTO create(CreateServiceDTO createServiceDTO) {
        Service service = createServiceDTO.convertToEntity();

        Car car = carRepository.findCarById(createServiceDTO.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + createServiceDTO.getCarId()));

        service.setCar(car);
        serviceRepository.save(service);
        return new ResponseServiceDTO(service);
    }

    public ResponseServiceDTO findById(UUID id) {
        Service service = serviceRepository.findServiceById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));
        return new ResponseServiceDTO(service);
    }

    public ResponseServiceDTO update(UUID id, UpdateServiceDTO updateServiceDTO) {
        Service service = updateServiceDTO.convertToEntity();
        service.setId(id);

        if (updateServiceDTO.getCarId() != null) {
            Car car = carRepository.findCarById(updateServiceDTO.getCarId())
                    .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + updateServiceDTO.getCarId()));
            service.setCar(car);
        }

        Service updatedService = serviceRepository.edit(service);
        return new ResponseServiceDTO(updatedService);
    }

    public void delete(UUID id) {
        serviceRepository.delete(id);
    }
}
