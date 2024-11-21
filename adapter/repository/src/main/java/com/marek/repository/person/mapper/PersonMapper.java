package com.marek.repository.person.mapper;

import com.marek.domain.person.dto.PersonDto;
import com.marek.repository.person.dbo.PersonDbo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<PersonDbo> {

    public static PersonDto toDto(PersonDbo person) {
        return PersonDto.builder()
                .id(person.id())
                .name(person.name())
                .hasAgreedToTerms(person.hasAgreedToTerms())
                .build();
    }

    @Override
    public PersonDbo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PersonDbo(
                rs.getString("id"),
                rs.getString("name"),
                rs.getBoolean("agreed_terms")
        );
    }
}
