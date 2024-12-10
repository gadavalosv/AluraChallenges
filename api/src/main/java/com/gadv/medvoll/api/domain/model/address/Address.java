package com.gadv.medvoll.api.domain.model.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String district;
    private String city;
    private String number;
    private String complement;

    public Address(AddressData address) {
        this.street = address.street();
        this.district = address.district();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
    }

    public Address updateData(AddressData address) {
        this.street = address.street();
        this.district = address.district();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
        return this;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }
}
