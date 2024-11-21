package com.marek.repository.personsector;

import com.marek.domain.personsector.dto.PersonSectorDto;
import com.marek.domain.personsector.port.GetPersonSectorsPort;
import com.marek.repository.personsector.mapper.PersonSectorsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPersonSectorsRepository implements GetPersonSectorsPort {
    private final JdbcClient jdbcClient;

    @Override
    public List<PersonSectorDto> execute(Query query) {
        var sqlQuery = """
                SELECT DISTINCT ON (s.id)
                    s.id AS sector_id,
                    s.name AS sector_name,
                    s.parent_id AS parent_sector_id
                FROM
                    person_sector ps
                INNER JOIN
                    person p ON ps.person_id = p.id
                INNER JOIN
                    sector s ON ps.sector_id = s.id
                WHERE
                    p.id = ?;
                """;

        return jdbcClient.sql(sqlQuery)
                .param(1, query.personId())
                .query(new PersonSectorsMapper())
                .stream()
                .map(PersonSectorsMapper::toDto)
                .toList();
    }
}
