package com.gadv.medvoll.api.domain.model.consult.validations;

import com.gadv.medvoll.api.domain.MedvollValidationException;
import com.gadv.medvoll.api.domain.model.consult.ConsultReserveData;
import com.gadv.medvoll.api.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OverlappingConsultForDoctorValidator implements ConsultValidator {
    @Autowired
    private ConsultRepository consultRepository;
    public void validate(ConsultReserveData consultReserveData){
        boolean doctorHasAnotherConsult = consultRepository.existsByDoctorIdAndConsultDate(
                consultReserveData.idDoctor(), consultReserveData.consultDate());

        if (doctorHasAnotherConsult) {
            throw new MedvollValidationException("Doctor ya tiene otra consulta en esa misma fecha y hora");
        }
    }
}
