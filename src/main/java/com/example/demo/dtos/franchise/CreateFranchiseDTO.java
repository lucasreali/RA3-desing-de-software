package com.example.demo.dtos.franchise;

import com.example.demo.models.Franchise;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFranchiseDTO {
    @NotNull
    @NotBlank
    private String localization;

    public Franchise convertToEntity() {
        Franchise franchise = new Franchise();
        franchise.setLocalization(localization);
        return franchise;
    }
}

