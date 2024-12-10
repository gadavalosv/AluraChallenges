package com.gadv.medvoll.api.domain.model.doctor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Specialty {
    ORTOPEDIA("Ortopedia"),
    CARDIOLOGIA("Cardiologia"),
    GINECOLOGIA("Ginecologia"),
    PEDIATRIA("Pediatria");

    private final String specialty;

    Specialty(String specialty) {
        this.specialty = specialty;
    }

    @JsonValue
    public String getSpecialty() {
        return specialty;
    }

    @JsonCreator
    public static Specialty fromString(String specialty) {
        for (Specialty smCategory : Specialty.values()) {
            if (smCategory.specialty.equalsIgnoreCase(specialty)) {
                return smCategory;
            }
        }
        throw new IllegalArgumentException("Ninguna Especialidad encontrada: " + specialty);
    }
}