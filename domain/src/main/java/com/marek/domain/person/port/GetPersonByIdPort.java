package com.marek.domain.person.port;

import com.marek.domain.person.dto.PersonDto;
import lombok.Builder;

public interface GetPersonByIdPort {
    PersonDto execute(Query query);

    @Builder
    record Query(String personId) {}
}
