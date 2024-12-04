package com.gadv.medvoll.api.model.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PatientListData(
        @NotBlank Long id,
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String identityDocument
) {
    public PatientListData(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getIdentityDocument());
    }
}
