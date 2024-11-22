package com.marek.application.fixtures;

import com.marek.model.form.SaveUserSectorsRequestV1;
import com.marek.model.sectors.GetPersonSectorsResponseV1;
import com.marek.model.sectors.PersonSector;
import com.marek.model.sectors.Sector;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestFixtures {
    public static final List<Sector> SECTORS_EXPECTED_RESPONSE =
            List.of(new Sector(1, "Manufacturing", List.of()),
                    new Sector(2, "Other", List.of()));

    public static final SaveUserSectorsRequestV1 VALID_SAVE_REQUEST = SaveUserSectorsRequestV1.builder()
            .name("Anti Depressant")
            .hasAgreedToTerms(true)
            .selectedSectors(List.of(1L)) // Selected 'Manufacturing'
            .build();

    public static final GetPersonSectorsResponseV1 VALID_SAVE_RESPONSE = GetPersonSectorsResponseV1.builder()
            .name("Anti Depressant")
            .hasAgreedToTerms(true)
            .sectors(List.of(new PersonSector(1, "Manufacturing"))) // Selected 'Manufacturing'
            .build();

    public static final SaveUserSectorsRequestV1 VALID_UPDATE_REQUEST = SaveUserSectorsRequestV1.builder()
            .name("Stiiven Siigal")
            .hasAgreedToTerms(true)
            .selectedSectors(List.of(2L)) // Selected 'Other'
            .build();

    public static final GetPersonSectorsResponseV1 VALID_UPDATE_RESPONSE = GetPersonSectorsResponseV1.builder()
            .name("Stiiven Siigal")
            .hasAgreedToTerms(true)
            .sectors(List.of(new PersonSector(2, "Other"))) // Selected 'Manufacturing'
            .build();

    public static final SaveUserSectorsRequestV1 INVALID_SAVE_REQUEST = SaveUserSectorsRequestV1.builder()
            .build();

    public static final Map<String, String> INVALID_SAVE_RESPONSE = Map.of("selectedSectors", "You must select at least one sector",
            "name", "Name must not be blank");

}
