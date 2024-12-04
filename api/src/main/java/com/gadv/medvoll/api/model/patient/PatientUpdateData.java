package com.gadv.medvoll.api.model.patient;

import com.gadv.medvoll.api.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateData(
        @NotNull Long id,
        String name,
        String phone,
        @Valid AddressData address
) {
}
