package com.marek.domain.sectors.mapper;

import com.marek.domain.sectors.dto.SectorDto;
import com.marek.model.sectors.Sector;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SectorMapper {

    public static Sector toModel(SectorDto dto) {
        var children = dto.children().stream().map(SectorMapper::toModel).toList();

        return Sector.builder()
                .id(dto.id())
                .name(dto.name())
                .children(children)
                .build();
    }
}
