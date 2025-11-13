package com.example.demo.dtos.franchise;

import com.example.demo.models.Franchise;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFranchiseDTO {
    private String localization;

    public Franchise convertToEntity() {
        Franchise franchise = new Franchise();
        franchise.setLocalization(localization != null && !localization.isBlank() ? localization : null);
        return franchise;
    }
}

