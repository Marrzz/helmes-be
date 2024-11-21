package com.marek.domain.personsector;

import com.marek.domain.personsector.port.SavePersonSectorsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePersonSectorsService implements SavePersonSectorsUseCase {
    private final SavePersonSectorsPort savePersonSectors;

    @Override
    public void execute(Payload payload) {
        savePersonSectors.execute(SavePersonSectorsPort.Payload.from(payload));
    }
}
