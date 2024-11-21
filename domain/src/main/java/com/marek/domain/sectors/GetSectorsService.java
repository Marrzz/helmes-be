package com.marek.domain.sectors;

import com.marek.domain.sectors.mapper.SectorMapper;
import com.marek.domain.sectors.port.GetSectorsPort;
import com.marek.model.sectors.Sector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSectorsService implements GetSectorsUseCase {
    private final GetSectorsPort getSectors;

    @Override
    public List<Sector> execute() {
        return getSectors.execute().stream().map(SectorMapper::toModel).toList();
    }
}
