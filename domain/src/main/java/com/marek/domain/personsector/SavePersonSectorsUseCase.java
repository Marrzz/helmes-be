package com.marek.domain.personsector;

import com.marek.model.form.SaveUserSectorsRequestV1;
import lombok.Builder;

import java.util.List;

public interface SavePersonSectorsUseCase {
    void execute(Payload payload);

    @Builder
    record Payload(String name, boolean hasAgreedToTerms, List<Long> selectedSectors, String personId) {

        public static Payload from(SaveUserSectorsRequestV1 request, String personId) {
            return Payload.builder()
                    .name(request.name())
                    .hasAgreedToTerms(request.hasAgreedToTerms())
                    .selectedSectors(request.selectedSectors())
                    .personId(personId)
                    .build();
        }
    }
}
