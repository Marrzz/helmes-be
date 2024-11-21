package com.marek.repository.person;

import com.marek.domain.person.port.SavePersonPort;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SavePersonRepository implements SavePersonPort {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void execute(Payload payload) {
        var statement = "insert into person (id, name, agreed_terms) values (?,?,?)";


        jdbcTemplate.update(statement,
                (ps) -> {
                    ps.setString(1, payload.sessionId());
                    ps.setString(2, payload.name());
                    ps.setBoolean(3, payload.hasAgreedToTerms());
                });
    }
}
