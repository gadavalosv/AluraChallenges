package com.gadv.medvoll.api.domain.model.patient;

import com.gadv.medvoll.api.domain.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientResponseData(
        @NotNull Long id,
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String identityDocument,
        @NotBlank String phone,
        @NotNull @Valid AddressData address
) {
    public PatientResponseData(Long id, String name, String email, String identityDocument, String phone, AddressData address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.identityDocument = identityDocument;
        this.phone = phone;
        this.address = address;
    }
}
