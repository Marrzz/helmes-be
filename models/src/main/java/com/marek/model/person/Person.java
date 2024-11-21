package com.marek.model.person;

import lombok.Builder;

@Builder
public record Person(String id, String name, boolean hasAgreedToTerms) {
}
