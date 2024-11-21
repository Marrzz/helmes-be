package com.marek.domain.personsector.port;

import com.marek.domain.personsector.dto.PersonSectorDto;
import lombok.Builder;

import java.util.List;

public interface GetPersonSectorsPort {
    List<PersonSectorDto> execute(Query query);

    @Builder
    record Query(String personId) {
    }
}
