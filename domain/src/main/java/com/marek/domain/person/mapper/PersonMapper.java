package com.marek.domain.person.mapper;


import com.marek.domain.person.dto.PersonDto;
import com.marek.model.person.Person;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonMapper {
    public static Person toModel(PersonDto person) {
        return Person.builder()
                .id(person.id())
                .name(person.name())
                .hasAgreedToTerms(person.hasAgreedToTerms())
                .build();
    }
}
