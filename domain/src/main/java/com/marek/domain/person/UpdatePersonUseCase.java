package com.marek.domain.person;

import lombok.Builder;

public interface UpdatePersonUseCase {
    void execute(Payload payload);

    @Builder
    record Payload(String name, boolean hasAgreedToTerms, String id) {
    }
}
