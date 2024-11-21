package com.marek.domain.person.port;

import com.marek.domain.person.SavePersonUseCase;

public interface SavePersonPort {
    void execute(Payload payload);


    record Payload(String name, String sessionId, boolean hasAgreedToTerms) {

        public static Payload from(SavePersonUseCase.Payload payload) {
            return new Payload(payload.name(), payload.personId(), payload.hasAgreedToTerms());
        }
    }
}
