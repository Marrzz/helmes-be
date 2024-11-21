package com.marek.repository.personsector;

import com.marek.domain.personsector.port.SavePersonSectorsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SavePersonSectorsRepository implements SavePersonSectorsPort {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void execute(Payload formData) {
        var statement = "INSERT INTO person_sector (person_id, sector_id) VALUES (?,?)";

        jdbcTemplate.batchUpdate(statement, formData.sectorIds(), 100,
                (var ps, var sectorId) -> {
                    ps.setString(1, formData.personId());
                    ps.setLong(2, sectorId);
                });
    }


}
