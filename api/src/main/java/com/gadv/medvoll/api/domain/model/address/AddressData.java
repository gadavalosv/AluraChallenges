package com.gadv.medvoll.api.domain.model.address;

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
        public AddressData(String street, String district, String city, String number, String complement) {
                this.street = street;
                this.district = district;
                this.city = city;
                this.number = number;
                this.complement = complement;
        }
}
