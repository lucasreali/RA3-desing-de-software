package com.example.demo.dtos.franchise;

import com.example.demo.models.Franchise;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponseFranchiseDTO {
    private final UUID id;
    private final String localization;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ResponseFranchiseDTO(Franchise franchise) {
        id = franchise.getId();
        localization = franchise.getLocalization();
        createdAt = franchise.getCreatedAt();
        updatedAt = franchise.getUpdatedAt();
    }
}

