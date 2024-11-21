package com.marek.repository.person;

import com.marek.domain.person.dto.PersonDto;
import com.marek.domain.person.port.GetPersonByIdPort;
import com.marek.repository.person.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class GetPersonByIdRepository implements GetPersonByIdPort {
    private final JdbcClient jdbcTemplate;

    @Override
    public PersonDto execute(Query query) {
        return jdbcTemplate.sql("SELECT * FROM person WHERE id = ?")
                .param(1, query.personId())
                .query(new PersonMapper())
                .optional()
                .map(PersonMapper::toDto)
                .orElse(PersonDto.builder().build());
    }
}
