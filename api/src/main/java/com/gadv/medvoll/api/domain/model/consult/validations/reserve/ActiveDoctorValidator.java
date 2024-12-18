package com.gadv.medvoll.api.domain.model.consult.validations.reserve;

import com.gadv.medvoll.api.domain.MedvollValidationException;
import com.gadv.medvoll.api.domain.model.consult.ConsultReserveData;
import com.gadv.medvoll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements ConsultValidator {
    @Autowired
    private DoctorRepository doctorRepository;
    public void validate(ConsultReserveData consultReserveData){
        if (consultReserveData.idDoctor() == null) return;
        var isDoctorActive = doctorRepository.findActiveById(consultReserveData.idDoctor());
        System.out.println(isDoctorActive);
        System.out.println(consultReserveData.idDoctor());
        if(!isDoctorActive){
            throw new MedvollValidationException("Consulta no puede ser reservada con médico excluido");
        }
    }
}
