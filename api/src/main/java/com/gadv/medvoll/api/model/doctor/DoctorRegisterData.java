package com.gadv.medvoll.api.model.doctor;

import com.gadv.medvoll.api.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegisterData(
        @NotBlank //includes @NotNull
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //número de 4 a 6 digitos
        String document,
        @NotBlank
        String phone,
        @NotNull
        Specialty specialty,
        @NotNull
        @Valid
        AddressData address
) {
}
