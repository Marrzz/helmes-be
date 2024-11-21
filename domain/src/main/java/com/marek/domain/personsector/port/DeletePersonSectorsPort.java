package com.marek.domain.personsector.port;

import lombok.Builder;

import java.util.List;

public interface DeletePersonSectorsPort {
    void execute(Payload payload);

    @Builder
    record Payload(List<Long> sectorIds, String personId) {

    }
}
