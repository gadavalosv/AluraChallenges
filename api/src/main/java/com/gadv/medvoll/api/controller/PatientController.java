package com.gadv.medvoll.api.controller;

import com.gadv.medvoll.api.model.patient.Patient;
import com.gadv.medvoll.api.model.patient.PatientListData;
import com.gadv.medvoll.api.model.patient.PatientRegisterData;
import com.gadv.medvoll.api.model.patient.PatientUpdateData;
import com.gadv.medvoll.api.repository.PatientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PatientRegisterData patientRegisterData){
        patientRepository.save(new Patient(patientRegisterData));
    }

    @GetMapping
    public Page<PatientListData> listPatients(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable pagination) {
        //return patientRepository.findAll(pagination).map(PatientListData::new);
        return patientRepository.findByActiveTrue(pagination).map(PatientListData::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid PatientUpdateData patientUpdateData){
        Patient patient = patientRepository.getReferenceById(patientUpdateData.id());
        patient.updateData(patientUpdateData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id){
        Patient patient = patientRepository.getReferenceById(id);
        patient.deActivate(); //FAKE DELETE
        //DELETE FROM DB: doctorRepository.delete(doctor);
    }
}
