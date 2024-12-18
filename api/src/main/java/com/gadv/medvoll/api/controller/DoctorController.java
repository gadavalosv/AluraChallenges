package com.gadv.medvoll.api.controller;

import com.gadv.medvoll.api.domain.model.address.AddressData;
import com.gadv.medvoll.api.domain.model.doctor.*;
import com.gadv.medvoll.api.repository.DoctorRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping
    public ResponseEntity<DoctorResponseData> registerDoctor(@RequestBody @Valid DoctorRegisterData doctorRegisterData, UriComponentsBuilder uriComponentsBuilder){
        Doctor doctor = doctorRepository.save(new Doctor(doctorRegisterData));
        DoctorResponseData doctorResponseData = new DoctorResponseData(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getDocument(), doctor.getPhone(), doctor.getSpecialty(),
                new AddressData(doctor.getAddress().getStreet(), doctor.getAddress().getDistrict(), doctor.getAddress().getCity(), doctor.getAddress().getNumber(), doctor.getAddress().getComplement()));
        URI url = uriComponentsBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(url).body(doctorResponseData);
    }
    //POST buenas practicas:
    // Return 201 Created
    // URL donde encontrar al doctor
    // GET http://localhost:8080/doctors/xx

    @GetMapping
    // Nota: Url example: http://localhost:8080/doctors?size=3&page=0&sort=document
    public ResponseEntity<Page<DoctorListData>> listDoctors(@PageableDefault(size = 2) Pageable pagination){ //PageableDefault sets default Pageable url params when not specified
        //return doctorRepository.findAll(pagination).map(DoctorListData::new);
        return ResponseEntity.ok(doctorRepository.findByActiveTrue(pagination).map(DoctorListData::new));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseData> listDoctorById(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        return ResponseEntity.ok(
                new DoctorResponseData(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getDocument(), doctor.getPhone(), doctor.getSpecialty(),
                new AddressData(doctor.getAddress().getStreet(), doctor.getAddress().getDistrict(), doctor.getAddress().getCity(), doctor.getAddress().getNumber(), doctor.getAddress().getComplement()))
        );
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorResponseData> updateDoctor(@RequestBody @Valid DoctorUpdateData doctorUpdateData){
        Doctor doctor = doctorRepository.getReferenceById(doctorUpdateData.id());
        doctor.updateData(doctorUpdateData);
        return ResponseEntity.ok(
                new DoctorResponseData(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getDocument(), doctor.getPhone(), doctor.getSpecialty(),
                new AddressData(doctor.getAddress().getStreet(), doctor.getAddress().getDistrict(), doctor.getAddress().getCity(), doctor.getAddress().getNumber(), doctor.getAddress().getComplement()))
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.deActivate();
        return ResponseEntity.noContent().build();
    }
}
