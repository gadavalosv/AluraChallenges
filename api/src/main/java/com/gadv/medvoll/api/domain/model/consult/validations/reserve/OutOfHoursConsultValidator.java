package com.gadv.medvoll.api.domain.model.consult.validations.reserve;

import com.gadv.medvoll.api.domain.MedvollValidationException;
import com.gadv.medvoll.api.domain.model.consult.ConsultReserveData;
import com.gadv.medvoll.api.domain.model.consult.validations.data.ValidatorsData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class OutOfHoursConsultValidator implements ConsultValidator {
    private final int consultDurationInHours = ValidatorsData.getConsultDurationInHours();
    private final int timeClinicOpens = ValidatorsData.getTimeClinicOpens();
    private final int timeClinicCloses = ValidatorsData.getTimeClinicCloses();
    public void validate(ConsultReserveData consultReserveData){
        var consultDate = consultReserveData.consultDate();
        var sunday = consultDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var scheduleBeforeClinicOpening = (consultDate.getHour() < timeClinicOpens);
        var scheduleAfterClinicClosing = (consultDate.getHour() > (timeClinicCloses - consultDurationInHours));
        if(sunday || scheduleBeforeClinicOpening || scheduleAfterClinicClosing){
            throw new MedvollValidationException("Horario seleccionado fuera del horario de atención de la clínica.");
        }
    }
}
