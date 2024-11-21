package com.marek.domain.personsector;

import com.marek.domain.personsector.mapper.PersonSectorMapper;
import com.marek.domain.personsector.port.GetPersonSectorsPort;
import com.marek.model.sectors.PersonSector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPersonSectorsService implements GetPersonSectorsUseCase {
    private final GetPersonSectorsPort getPersonSectors;

    @Override
    public List<PersonSector> execute(Query query) {
        var personSectorsQuery = GetPersonSectorsPort.Query.builder()
                .personId(query.personId())
                .build();
        return getPersonSectors.execute(personSectorsQuery).stream()
                .map(PersonSectorMapper::toModel)
                .toList();
    }
}
