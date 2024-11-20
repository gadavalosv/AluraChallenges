package com.gadv.medvoll.api.model.address;

public record AddressData(
        String street,
        String district,
        String city,
        String number,
        String complement
) {
}
