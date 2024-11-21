package com.marek.repository.person.dbo;

import lombok.Builder;

@Builder
public record PersonDbo(String id, String name, boolean hasAgreedToTerms) {
}
