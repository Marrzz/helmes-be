package com.marek.domain.person;

import com.marek.domain.person.port.UpdatePersonPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePersonService implements UpdatePersonUseCase {
    private final UpdatePersonPort updatePersonPort;

    @Override
    public void execute(Payload payload) {
        var data = UpdatePersonPort.Payload.builder()
                .name(payload.name())
                .hasAgreedToTerms(payload.hasAgreedToTerms())
                .personId(payload.id())
                .build();
        updatePersonPort.execute(data);
    }
}
