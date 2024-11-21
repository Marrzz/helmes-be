package com.marek.domain.personsector;

import com.marek.model.sectors.PersonSector;
import lombok.Builder;

import java.util.List;

public interface GetPersonSectorsUseCase {
    List<PersonSector> execute(Query query);

    @Builder
    record Query(String personId) {
    }
}
