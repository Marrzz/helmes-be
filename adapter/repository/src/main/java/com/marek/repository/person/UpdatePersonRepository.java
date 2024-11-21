package com.marek.repository.person;

import com.marek.domain.person.port.UpdatePersonPort;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UpdatePersonRepository implements UpdatePersonPort {
    private final JdbcClient jdbcClient;


    @Override
    public void execute(Payload payload) {
        var statement = "update person set name = ?, agreed_terms = ? where id = ?";

        jdbcClient.sql(statement)
                .param(1, payload.name())
                .param(2, payload.hasAgreedToTerms())
                .param(3, payload.personId())
                .update();
    }
}
