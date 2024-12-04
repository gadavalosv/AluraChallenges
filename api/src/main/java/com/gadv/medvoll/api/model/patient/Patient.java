package com.gadv.medvoll.api.model.patient;

import com.gadv.medvoll.api.model.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Patient")
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String identityDocument;
    private String phone;
    @Embedded
    private Address address;
    private Boolean active;
    public Patient(PatientRegisterData patientRegisterData) {
        this.name = patientRegisterData.name();
        this.email = patientRegisterData.email();
        this.phone = patientRegisterData.phone();
        this.identityDocument = patientRegisterData.identityDocument();
        this.address = new Address(patientRegisterData.address());
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getIdentityDocument(){
        return this.identityDocument;
    }

    public void updateData(PatientUpdateData patientUpdateData) {
        this.name = (patientUpdateData.name() != null && !patientUpdateData.name().isEmpty()) ? patientUpdateData.name(): this.name;
        this.phone = (patientUpdateData.phone() != null && !patientUpdateData.phone().isEmpty()) ? patientUpdateData.phone(): this.phone;
        this.address = (patientUpdateData.address()!= null) ? address.updateData(patientUpdateData.address()) : this.address;
    }

    public void deActivate() {
        this.active = false;
    }
}

