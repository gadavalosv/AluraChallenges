package com.gadv.medvoll.api.model.patient;

import com.gadv.medvoll.api.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientRegisterData(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String identityDocument,
        @NotBlank String phone,
        @NotNull @Valid AddressData address
) {
}
