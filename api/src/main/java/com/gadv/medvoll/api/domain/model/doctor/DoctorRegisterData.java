package com.gadv.medvoll.api.domain.model.doctor;

import com.gadv.medvoll.api.domain.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegisterData(
        @NotBlank(message = "{name.required}") //includes @NotNull
        String name,
        @NotBlank(message = "{email.required}")
        @Email(message = "{email.invalid}")
        String email,
        @NotBlank(message = "{document.required}")
        @Pattern(regexp = "\\d{4,6}", message = "{document.invalid}") //número de 4 a 6 digitos
        String document,
        @NotBlank(message = "{phone.required}")
        String phone,
        @NotNull(message = "{specialty.required}")
        Specialty specialty,
        @NotNull(message = "{address.required}")
        @Valid
        AddressData address
) {
}
