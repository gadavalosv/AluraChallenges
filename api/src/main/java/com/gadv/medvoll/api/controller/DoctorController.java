package com.gadv.medvoll.api.controller;

import com.gadv.medvoll.api.model.doctor.Doctor;
import com.gadv.medvoll.api.model.doctor.DoctorListData;
import com.gadv.medvoll.api.model.doctor.DoctorRegisterData;
import com.gadv.medvoll.api.model.doctor.DoctorUpdateData;
import com.gadv.medvoll.api.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping
    public void registerDoctor(@RequestBody @Valid DoctorRegisterData doctorRegisterData){
        doctorRepository.save(new Doctor(doctorRegisterData));
    }

    @GetMapping
    // Nota: Url example: http://localhost:8080/doctors?size=3&page=0&sort=document
    public Page<DoctorListData> listDoctors(@PageableDefault(size = 2) Pageable pagination){ //PageableDefault sets default Pageable url params when not specified
        //return doctorRepository.findAll(pagination).map(DoctorListData::new);
        return doctorRepository.findByActiveTrue(pagination).map(DoctorListData::new); //Fake delete applied
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid DoctorUpdateData doctorUpdateData){
        Doctor doctor = doctorRepository.getReferenceById(doctorUpdateData.id());
        doctor.updateData(doctorUpdateData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.deActivate(); //FAKE DELETE
        //DELETE FROM DB: doctorRepository.delete(doctor);
    }
}
