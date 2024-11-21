package com.marek.repository.sectors;

import com.marek.domain.sectors.dto.SectorDto;
import com.marek.domain.sectors.port.GetSectorsPort;
import com.marek.repository.sectors.mapper.SectorsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class GetSectorsRepository implements GetSectorsPort {
    private final JdbcClient jdbcClient;


    @Override
    public List<SectorDto> execute() {
        var sectorsQuery = "SELECT DISTINCT ON (id) * FROM sector;";

        var sectors = jdbcClient.sql(sectorsQuery)
                .query(new SectorsMapper())
                .list();

        return SectorsMapper.groupSectors(sectors);

    }
}
