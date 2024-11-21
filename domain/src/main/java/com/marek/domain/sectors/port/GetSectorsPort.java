package com.marek.domain.sectors.port;

import com.marek.domain.sectors.dto.SectorDto;

import java.util.List;

public interface GetSectorsPort {
    List<SectorDto> execute();
}
