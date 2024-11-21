package com.marek.repository.sectors.dbo;

import lombok.Builder;

@Builder
public record SectorDbo(long id, String name, Long parentId) {
}
