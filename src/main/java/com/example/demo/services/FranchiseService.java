package com.example.demo.services;

import com.example.demo.dtos.franchise.CreateFranchiseDTO;
import com.example.demo.dtos.franchise.ResponseFranchiseDTO;
import com.example.demo.dtos.franchise.UpdateFranchiseDTO;
import com.example.demo.models.Franchise;
import com.example.demo.repositories.FranchiseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FranchiseService {
    private final FranchiseRepository franchiseRepository;

    public FranchiseService(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public ResponseFranchiseDTO create(CreateFranchiseDTO createFranchiseDTO) {
        Franchise franchise = createFranchiseDTO.convertToEntity();
        franchiseRepository.save(franchise);
        return new ResponseFranchiseDTO(franchise);
    }

    public ResponseFranchiseDTO findById(UUID id) {
        Franchise franchise = franchiseRepository.findFranchiseById(id)
                .orElseThrow(() -> new EntityNotFoundException("Franchise not found with id: " + id));
        return new ResponseFranchiseDTO(franchise);
    }

    public ResponseFranchiseDTO update(UUID id, UpdateFranchiseDTO updateFranchiseDTO) {
        Franchise franchise = updateFranchiseDTO.convertToEntity();
        franchise.setId(id);
        Franchise updatedFranchise = franchiseRepository.edit(franchise);
        return new ResponseFranchiseDTO(updatedFranchise);
    }

    public void delete(UUID id) {
        franchiseRepository.delete(id);
    }
}

