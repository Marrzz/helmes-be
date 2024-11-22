package com.marek.model.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record UpdateUserSectorsRequestV1(
        @NotBlank(message = "Name must not be blank")
        String name,

        @NotNull(message = "You have to agree to terms" )
        @AssertTrue(message = "You have to agree to terms")
        boolean hasAgreedToTerms,

        @NotEmpty(message = "You must select at least one sector")
        List<Long> selectedSectors) {
}

