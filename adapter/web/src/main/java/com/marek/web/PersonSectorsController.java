package com.marek.web;

import com.marek.domain.person.GetPersonByIdUseCase;
import com.marek.domain.person.SavePersonUseCase;
import com.marek.domain.person.UpdatePersonUseCase;
import com.marek.domain.personsector.GetPersonSectorsUseCase;
import com.marek.domain.personsector.SavePersonSectorsUseCase;
import com.marek.domain.personsector.UpdatePersonSectorsUseCase;
import com.marek.model.form.SaveUserSectorsRequestV1;
import com.marek.model.form.UpdateUserSectorsRequestV1;
import com.marek.model.sectors.GetPersonSectorsResponseV1;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping(PersonSectorsController.REQUEST_MAPPING)
public class PersonSectorsController {
    public static final String REQUEST_MAPPING = "/api/person";

    private final SavePersonUseCase savePerson;
    private final UpdatePersonUseCase updatePerson;
    private final GetPersonByIdUseCase getPersonById;
    private final GetPersonSectorsUseCase getPersonSectors;
    private final SavePersonSectorsUseCase saveUserSectors;
    private final UpdatePersonSectorsUseCase updateUserSectors;

    @GetMapping()
    public ResponseEntity<GetPersonSectorsResponseV1> get(HttpSession session) {
        var personSectorsQuery = GetPersonSectorsUseCase.Query.builder()
                .personId(session.getId())
                .build();
        var personQuery = GetPersonByIdUseCase.Query.builder()
                .personId(session.getId())
                .build();
        var sectors = getPersonSectors.execute(personSectorsQuery).stream()
                .toList();

        var person = getPersonById.execute(personQuery);
        var response = GetPersonSectorsResponseV1.builder()
                .sectors(sectors)
                .name(person.name())
                .hasAgreedToTerms(person.hasAgreedToTerms())
                .id(person.id())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<Void> save(@Valid @RequestBody SaveUserSectorsRequestV1 request, HttpSession session) {
        var savePersonPayload = SavePersonUseCase.Payload.builder()
                .name(request.name())
                .hasAgreedToTerms(request.hasAgreedToTerms())
                .personId(session.getId())
                .build();
        var saveUserSectorsPayload = SavePersonSectorsUseCase.Payload
                .from(request, session.getId());

        savePerson.execute(savePersonPayload);
        saveUserSectors.execute(saveUserSectorsPayload);

        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateUserSectorsRequestV1 request, HttpSession session) {
        var updatePayload = UpdatePersonUseCase.Payload.builder()
                .name(request.name())
                .hasAgreedToTerms(request.hasAgreedToTerms())
                .id(session.getId())
                .build();
        var updateUserSectorsPayload = UpdatePersonSectorsUseCase.Payload
                .from(request, session.getId());


        updateUserSectors.execute(updateUserSectorsPayload);
        updatePerson.execute(updatePayload);

        return ResponseEntity.ok().build();
    }


}
