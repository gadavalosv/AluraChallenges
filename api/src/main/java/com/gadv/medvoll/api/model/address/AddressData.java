package com.gadv.medvoll.api.model.address;

import jakarta.validation.constraints.NotBlank;

public record AddressData(
        @NotBlank
        String street,
        @NotBlank
        String district,
        @NotBlank
        String city,
        @NotBlank
        String number,
        @NotBlank
        String complement
) {
}
