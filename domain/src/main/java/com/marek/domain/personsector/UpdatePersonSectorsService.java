package com.marek.domain.personsector;

import com.marek.domain.personsector.dto.PersonSectorDto;
import com.marek.domain.personsector.port.GetPersonSectorsPort;
import com.marek.domain.personsector.port.SavePersonSectorsPort;
import com.marek.domain.personsector.port.DeletePersonSectorsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdatePersonSectorsService implements UpdatePersonSectorsUseCase {
    private final GetPersonSectorsPort getPersonSectors;
    private final SavePersonSectorsPort savePersonSectors;
    private final DeletePersonSectorsPort deletePersonSectors;

    @Override
    public void execute(Payload payload) {
        var sectorsQuery = GetPersonSectorsPort.Query.builder()
                .personId(payload.personId())
                .build();

        var userSectors = getPersonSectors.execute(sectorsQuery).stream()
                .map(PersonSectorDto::id)
                .collect(Collectors.toSet());

        var sectorsToAdd = payload.selectedSectors().stream()
                .filter(sector -> !userSectors.contains(sector))
                .toList();

       var sectorsToRemove = userSectors.stream()
                .filter(sector -> !payload.selectedSectors().contains(sector))
                .toList();

        addNewSectorsToUser(sectorsToAdd, payload.personId());
        removeSectorsFromUser(sectorsToRemove, payload.personId());

    }

    private void addNewSectorsToUser(List<Long> sectorIds, String personId) {
        var payload = SavePersonSectorsPort.Payload.builder()
                .sectorIds(sectorIds)
                .personId(personId)
                .build();

        savePersonSectors.execute(payload);
    }

    private void removeSectorsFromUser(List<Long> sectorIds, String personId) {
        var payload = DeletePersonSectorsPort.Payload.builder()
                .personId(personId)
                .sectorIds(sectorIds)
                .build();

        deletePersonSectors.execute(payload);
    }
}
