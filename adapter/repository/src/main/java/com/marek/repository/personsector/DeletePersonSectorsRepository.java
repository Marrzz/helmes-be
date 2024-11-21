package com.marek.repository.personsector;

import com.marek.domain.personsector.port.DeletePersonSectorsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePersonSectorsRepository implements DeletePersonSectorsPort {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void execute(Payload payload) {
        var statement = "delete from person_sector where sector_id = ? and person_id = ?";

        jdbcTemplate.batchUpdate(statement, payload.sectorIds(), 100, (var ps, var sectorId) -> {
            ps.setLong(1, sectorId);
            ps.setString(2, payload.personId());
        });

    }
}
