package com.marek.model.sectors;

import lombok.Builder;

import java.util.List;

@Builder
public record GetPersonSectorsResponseV1(String id, String name, boolean hasAgreedToTerms, List<PersonSector> sectors) {
}
