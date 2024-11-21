package com.marek.repository.personsector.dbo;

import lombok.Builder;

@Builder
public record PersonSectorDbo(long id, String name, long parentId) {
}
