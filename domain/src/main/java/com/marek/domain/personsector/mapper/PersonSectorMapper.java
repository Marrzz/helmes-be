package com.marek.domain.personsector.mapper;

import com.marek.domain.personsector.dto.PersonSectorDto;
import com.marek.model.sectors.PersonSector;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonSectorMapper {

    public static PersonSector toModel(PersonSectorDto dto) {
        return PersonSector.builder()
                .id(dto.id())
                .name(dto.name())
                .build();
    }
}
