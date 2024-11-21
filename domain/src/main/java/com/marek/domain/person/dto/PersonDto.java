package com.marek.domain.person.dto;

import lombok.Builder;

@Builder
public record PersonDto(String id, String name, boolean hasAgreedToTerms) {
}
