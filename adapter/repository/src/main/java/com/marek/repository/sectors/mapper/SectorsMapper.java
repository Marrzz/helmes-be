package com.marek.repository.sectors.mapper;

import com.marek.domain.sectors.dto.SectorDto;
import com.marek.repository.sectors.dbo.SectorDbo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SectorsMapper implements RowMapper<SectorDbo> {

    @Override
    public SectorDbo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return SectorDbo.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .parentId(rs.getObject("parent_id", Long.class))
                .build();

    }

    public static List<SectorDto> groupSectors(List<SectorDbo> sectors) {
        var sectorsById = sectors.stream()
                .collect(Collectors.toMap(SectorDbo::id, SectorsMapper::mapSectorDbo));
        Function<Long, SectorDto> getSectorById = sectorsById::get;

        sectors.forEach(sector -> {
            Optional.ofNullable(sector.parentId())
                    .ifPresent(parentId -> getSectorById.apply(parentId).children().add(getSectorById.apply(sector.id())));
        });

        return sectors.stream()
                .filter(sector -> sector.parentId() == null)
                .map(sector -> getSectorById.apply(sector.id()))
                .toList();
    }

    public static SectorDto mapSectorDbo(SectorDbo sector) {
        return SectorDto.builder().id(sector.id()).name(sector.name()).children(new ArrayList<>()).build();
    }

    public static List<SectorDto> mapSectorDbos(List<SectorDbo> sector) {
        return sector.stream().map(SectorsMapper::mapSectorDbo).toList();
    }

}
