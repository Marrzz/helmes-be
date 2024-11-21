package com.marek.domain.person;

import com.marek.domain.person.mapper.PersonMapper;
import com.marek.domain.person.port.GetPersonByIdPort;
import com.marek.model.person.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPersonByIdService implements GetPersonByIdUseCase {
    private final GetPersonByIdPort getPersonById;

    @Override
    public Person execute(Query query) {
        var personQuery = GetPersonByIdPort.Query.builder()
                .personId(query.personId())
                .build();

        var personDto = getPersonById.execute(personQuery);
        return PersonMapper.toModel(personDto);
    }
}
