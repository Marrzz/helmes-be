package com.marek.domain.person;

import com.marek.model.person.Person;
import lombok.Builder;

public interface GetPersonByIdUseCase {
    Person execute(Query query);

    @Builder
    record Query(String personId) {}
}
