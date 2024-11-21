package com.marek.domain.sectors.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SectorDto(long id, String name, List<SectorDto> children) {
}
