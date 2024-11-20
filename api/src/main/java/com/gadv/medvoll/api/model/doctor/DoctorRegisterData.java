package com.gadv.medvoll.api.model.doctor;

import com.gadv.medvoll.api.model.address.AddressData;
import com.gadv.medvoll.api.model.doctor.Specialty;

public record DoctorRegisterData(
        String name,
        String email,
        String document,
        Specialty specialty,
        AddressData address
) {
}
