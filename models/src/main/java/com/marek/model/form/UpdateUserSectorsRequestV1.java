package com.marek.model.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record UpdateUserSectorsRequestV1(
        @NotBlank(message = "Name must not be blank")
        String name,
        boolean hasAgreedToTerms,
        @NotEmpty(message = "You must select at least one sector")
        List<Long> selectedSectors) {
}

