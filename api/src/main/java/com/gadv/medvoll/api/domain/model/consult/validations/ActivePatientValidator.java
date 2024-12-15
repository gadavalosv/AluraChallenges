package com.gadv.medvoll.api.domain.model.consult.validations;

import com.gadv.medvoll.api.domain.MedvollValidationException;
import com.gadv.medvoll.api.domain.model.consult.ConsultReserveData;
import com.gadv.medvoll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements ConsultValidator {
    @Autowired
    private PatientRepository patientRepository;

    public void validate(ConsultReserveData consultReserveData){
        var isPatientActive = patientRepository.findActiveById(consultReserveData.idPatient());
        if(!isPatientActive){
            throw new MedvollValidationException("Consulta no puede ser reservada con pacientes excluidos");
        }
    }
}
