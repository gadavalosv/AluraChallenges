package com.gadv.medvoll.api.controller;

import com.gadv.medvoll.api.domain.model.address.AddressData;
import com.gadv.medvoll.api.domain.model.patient.*;
import com.gadv.medvoll.api.repository.PatientRepository;
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
@RequestMapping("patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientResponseData> register(@RequestBody @Valid PatientRegisterData patientRegisterData, UriComponentsBuilder uriComponentsBuilder){
        Patient patient = patientRepository.save(new Patient(patientRegisterData));
        URI url = uriComponentsBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(url).body(
                new PatientResponseData(patient.getId(), patient.getName(), patient.getEmail(), patient.getIdentityDocument(), patient.getPhone(),
                new AddressData(patient.getAddress().getStreet(), patient.getAddress().getDistrict(), patient.getAddress().getCity(), patient.getAddress().getNumber(), patient.getAddress().getComplement()))
        );
    }

    @GetMapping
    public ResponseEntity<Page<PatientListData>> listPatients(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable pagination) {
        //return patientRepository.findAll(pagination).map(PatientListData::new);
        return ResponseEntity.ok(patientRepository.findByActiveTrue(pagination).map(PatientListData::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseData> listPatientById(@PathVariable Long id){
        Patient patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(
                new PatientResponseData(patient.getId(), patient.getName(), patient.getEmail(), patient.getIdentityDocument(), patient.getPhone(),
                new AddressData(patient.getAddress().getStreet(), patient.getAddress().getDistrict(), patient.getAddress().getCity(), patient.getAddress().getNumber(), patient.getAddress().getComplement()))
        );
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientResponseData> updatePatient(@RequestBody @Valid PatientUpdateData patientUpdateData){
        Patient patient = patientRepository.getReferenceById(patientUpdateData.id());
        patient.updateData(patientUpdateData);
        return ResponseEntity.ok(
                new PatientResponseData(patient.getId(), patient.getName(), patient.getEmail(), patient.getIdentityDocument(), patient.getPhone(),
                new AddressData(patient.getAddress().getStreet(), patient.getAddress().getDistrict(), patient.getAddress().getCity(), patient.getAddress().getNumber(), patient.getAddress().getComplement()))
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id){
        Patient patient = patientRepository.getReferenceById(id);
        patient.deActivate();
        return ResponseEntity.noContent().build();
    }
}
