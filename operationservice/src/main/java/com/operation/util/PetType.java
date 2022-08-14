package com.operation.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PetType {

    DOG(1, "Dog"),
    CAT(2, "Cat"),
    BIRD(3, "Bird");

    private final Integer petTypeId;
    private final String petTypeDescription;

    PetType(Integer petTypeId, String petTypeDescription) {
        this.petTypeId = petTypeId;
        this.petTypeDescription = petTypeDescription;
    }

    public Integer getPetTypeId() {
        return petTypeId;
    }

    public String getPetTypeDescription() {
        return petTypeDescription;
    }

    public static PetType findOne(Integer petTypeId) {
        return Arrays.stream(PetType.values())
                .filter(x -> x.petTypeId.equals(petTypeId))
                .findFirst()
                .orElse(null);
    }

}
