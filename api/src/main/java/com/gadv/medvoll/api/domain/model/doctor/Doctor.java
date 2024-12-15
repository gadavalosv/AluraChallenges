package com.gadv.medvoll.api.domain.model.doctor;


import com.gadv.medvoll.api.domain.model.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctors")
@Entity(name = "Doctor")
//LOMBOK to generate code without writing:
//IN ORDER TO WORK INSTALL LOMBOK PLUGIN & ENABLE ANNOTATION PROCESSING
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //determines how to compare between doctors
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String document;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;
    private Boolean active;

    public Doctor(DoctorRegisterData doctorRegisterData) {
        this.name = doctorRegisterData.name();
        this.email = doctorRegisterData.email();
        this.document = doctorRegisterData.document();
        this.phone = doctorRegisterData.phone();
        this.specialty = doctorRegisterData.specialty();
        this.address = new Address(doctorRegisterData.address());
        this.active = true;
    }

    public void updateData(DoctorUpdateData doctorUpdateData) {
        this.name = (doctorUpdateData.name() != null && !doctorUpdateData.name().isEmpty()) ? doctorUpdateData.name() : this.name;
        this.phone = (doctorUpdateData.phone() != null && !doctorUpdateData.phone().isEmpty()) ? doctorUpdateData.phone() : this.phone;
        this.address = (doctorUpdateData.address()!= null) ? address.updateData(doctorUpdateData.address()) : this.address;
    }

    public void deActivate() {
        this.active = false;
    }
}
