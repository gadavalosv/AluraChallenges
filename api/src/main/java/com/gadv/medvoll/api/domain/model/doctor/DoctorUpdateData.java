package com.gadv.medvoll.api.domain.model.doctor;

import com.gadv.medvoll.api.domain.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateData(
        @NotNull
        Long id,
        String name,
        String phone,
        @Valid
        AddressData address
) {
}
