package com.marek.domain.personsector;

import com.marek.model.form.UpdateUserSectorsRequestV1;
import lombok.Builder;

import java.util.List;

public interface UpdatePersonSectorsUseCase {
    void execute(Payload payload);

    @Builder
    record Payload(String name, boolean hasAgreedToTerms, List<Long> selectedSectors, String personId) {

        public static Payload from(UpdateUserSectorsRequestV1 request, String personId) {
            return Payload.builder()
                    .name(request.name())
                    .hasAgreedToTerms(request.hasAgreedToTerms())
                    .selectedSectors(request.selectedSectors())
                    .personId(personId)
                    .build();
        }
    }

}
