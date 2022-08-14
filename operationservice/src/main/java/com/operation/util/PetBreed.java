package com.operation.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PetBreed {

    GOLDEN_RETRIEVER(1, "golden retriever"),
    BULLDOG(2, "bulldog"),
    BEAGLE(3, "beagle"),
    ABYSSINIAN(4, "Abyssinian"),
    BOBTAIL(5, "Bobtail"),
    BOMBAY(6, "Bombay"),
    GREY_PARROT(7, "Grey Parrot"),
    BROWN_NODDY(8, "Brown Noddy"),
    BORDER_CANARY(9, "Border Canary"),
    OTHER(10, "OTHER");

    private final Integer petBreedId;
    private final String petBreedDescription;

    PetBreed(Integer petBreedId, String petBreedDescription) {
        this.petBreedId = petBreedId;
        this.petBreedDescription = petBreedDescription;
    }

    public Integer getPetBreedId() {
        return petBreedId;
    }

    public String getPetBreedDescription() {
        return petBreedDescription;
    }

    public static PetBreed findOne(Integer petBreedId) {
        return Arrays.stream(PetBreed.values())
                .filter(x -> x.petBreedId.equals(petBreedId))
                .findFirst()
                .orElse(null);
    }

}
