package com.marek.domain.personsector.port;

import com.marek.domain.personsector.SavePersonSectorsUseCase;
import lombok.Builder;

import java.util.List;

public interface SavePersonSectorsPort {
    void execute(Payload formData);

    @Builder
    record Payload(List<Long> sectorIds, String personId) {

        public static Payload from(SavePersonSectorsUseCase.Payload payload) {
            return Payload.builder()
                    .sectorIds(payload.selectedSectors())
                    .personId(payload.personId())
                    .build();
        }
    }
}
