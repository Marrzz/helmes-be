package com.marek.repository.personsector.mapper;

import com.marek.domain.personsector.dto.PersonSectorDto;
import com.marek.repository.personsector.dbo.PersonSectorDbo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonSectorsMapper implements RowMapper<PersonSectorDbo> {

    @Override
    public PersonSectorDbo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PersonSectorDbo.builder()
                .id(rs.getLong("sector_id"))
                .name(rs.getString("sector_name"))
                .parentId(rs.getLong("parent_sector_id"))
                .build();
    }

    public static PersonSectorDto toDto(PersonSectorDbo dbo) {
        return PersonSectorDto.builder()
                .id(dbo.id())
                .name(dbo.name())
                .build();
    }
}
