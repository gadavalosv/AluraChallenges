package com.gadv.medvoll.api.domain.model.doctor;

import com.gadv.medvoll.api.domain.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorResponseData(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String document,
        @NotBlank
        String phone,
        @NotNull
        Specialty specialty,
        @NotNull
        @Valid
        AddressData address
) {
    public DoctorResponseData(Long id, String name, String email, String document, String phone, Specialty specialty, AddressData address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.phone = phone;
        this.specialty = specialty;
        this.address = address;
    }
}
