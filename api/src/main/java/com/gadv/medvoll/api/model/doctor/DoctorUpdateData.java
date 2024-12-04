package com.gadv.medvoll.api.model.doctor;

import com.gadv.medvoll.api.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorUpdateData(
        @NotNull
        Long id,
        String name,
        String phone,
        @Valid
        AddressData address
) {
}
