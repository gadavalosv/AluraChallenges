package com.gadv.medvoll.api.controller;

import com.gadv.medvoll.api.model.doctor.DoctorRegisterData;
import com.gadv.medvoll.api.model.doctor.Specialty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @PostMapping
    public void registerDoctor(@RequestBody DoctorRegisterData doctorRegisterData){
        System.out.println(doctorRegisterData);
    }
}
