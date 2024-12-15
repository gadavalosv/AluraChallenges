package com.gadv.medvoll.api.domain.model.consult.validations;

import com.gadv.medvoll.api.domain.MedvollValidationException;
import com.gadv.medvoll.api.domain.model.consult.ConsultReserveData;
import com.gadv.medvoll.api.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NoSameDayConsultForPatientValidator implements ConsultValidator {
    @Autowired
    private ConsultRepository consultRepository;

    private final int
            consultDurationInHours = ValidatorsData.getConsultDurationInHours(),
            timeClinicOpens = ValidatorsData.getTimeClinicOpens(),
            timeClinicCloses = ValidatorsData.getTimeClinicCloses();
    @Autowired
    public void validate(ConsultReserveData consultReserveData){
        LocalDateTime firstSchedule = consultReserveData.consultDate().withHour(timeClinicOpens);
        LocalDateTime lastSChedule = consultReserveData.consultDate().withHour(timeClinicCloses - consultDurationInHours);
        boolean patientHasOtherConsult = consultRepository.existsByPatientIdAndConsultDateBetween(
                consultReserveData.idPatient(), firstSchedule, lastSChedule);

        if (patientHasOtherConsult) {
            throw new MedvollValidationException("Paciente ya tiene una consulta reservada para ese día.");
        }
    }
}
