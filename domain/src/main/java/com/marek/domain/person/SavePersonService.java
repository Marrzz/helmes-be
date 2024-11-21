package com.marek.domain.person;

import com.marek.domain.person.port.SavePersonPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePersonService implements SavePersonUseCase {
    private final SavePersonPort savePerson;

    @Override
    public void execute(Payload payload) {
        savePerson.execute(SavePersonPort.Payload.from(payload));
    }
}
