package com.marek.domain.person.port;

import lombok.Builder;

public interface UpdatePersonPort {
    void execute(Payload payload);

    @Builder
    record Payload(String name, boolean hasAgreedToTerms, String personId) {
    }
}
