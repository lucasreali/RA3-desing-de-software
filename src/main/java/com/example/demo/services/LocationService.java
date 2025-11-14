package com.example.demo.services;

import com.example.demo.dtos.location.CreateLocationDTO;
import com.example.demo.dtos.location.ResponseLocationDTO;
import com.example.demo.dtos.location.UpdateLocationDTO;
import com.example.demo.models.Car;
import com.example.demo.models.Customer;
import com.example.demo.models.Location;
import com.example.demo.models.PaymentMethod;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.LocationRepository;
import com.example.demo.repositories.PaymentMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LocationService {
    private final CustomerRepository customerRepository;
    private final LocationRepository locationRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final CarRepository carRepository;

    @Transactional
    public ResponseLocationDTO create(CreateLocationDTO createLocationDTO) {
        Customer customer = customerRepository.findCustomerById(createLocationDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + createLocationDTO.getCustomerId()));
        Car car = carRepository.findCarById(createLocationDTO.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + createLocationDTO.getCarId()));
        PaymentMethod paymentMethod = paymentMethodRepository.findPaymentMethodById(createLocationDTO.getPaymentMethodId())
                .orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found with id: " + createLocationDTO.getPaymentMethodId()));

        Location location = createLocationDTO.convertToEntity();
        location.setCustomer(customer);
        location.setCar(car);
        location.setPaymentMethod(paymentMethod);

        locationRepository.save(location);
        return new ResponseLocationDTO(location);
    }

    public ResponseLocationDTO findById(UUID id) {
        Location location = locationRepository.findLocationById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + id));
        return new ResponseLocationDTO(location);
    }

    public ResponseLocationDTO update(UUID id, UpdateLocationDTO updateLocationDTO) {
        Location partial = new Location();
        partial.setId(id);

        if (updateLocationDTO.getValue() != null) {
            partial.setValue(updateLocationDTO.getValue());
        }
        if (updateLocationDTO.getExpiration() != null) {
            partial.setExpiration(updateLocationDTO.getExpiration());
        }
        if (updateLocationDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findCustomerById(updateLocationDTO.getCustomerId())
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + updateLocationDTO.getCustomerId()));
            partial.setCustomer(customer);
        }
        if (updateLocationDTO.getCarId() != null) {
            Car car = carRepository.findCarById(updateLocationDTO.getCarId())
                    .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + updateLocationDTO.getCarId()));
            partial.setCar(car);
        }
        if (updateLocationDTO.getPaymentMethodId() != null) {
            PaymentMethod paymentMethod = paymentMethodRepository.findPaymentMethodById(updateLocationDTO.getPaymentMethodId())
                    .orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found with id: " + updateLocationDTO.getPaymentMethodId()));
            partial.setPaymentMethod(paymentMethod);
        }

        Location updated = locationRepository.edit(partial);
        return new ResponseLocationDTO(updated);
    }

    public void delete(UUID id) {
        locationRepository.delete(id);
    }
}
