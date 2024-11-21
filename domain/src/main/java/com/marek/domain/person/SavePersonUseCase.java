package com.marek.domain.person;

import lombok.Builder;

public interface SavePersonUseCase {
    void execute(Payload payload);

    @Builder
    record Payload(String personId, String name, boolean hasAgreedToTerms) {
    }
}
