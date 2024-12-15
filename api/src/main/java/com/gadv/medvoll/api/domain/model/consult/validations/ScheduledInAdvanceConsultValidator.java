package com.gadv.medvoll.api.domain.model.consult.validations;

import com.gadv.medvoll.api.domain.MedvollValidationException;
import com.gadv.medvoll.api.domain.model.consult.ConsultReserveData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ScheduledInAdvanceConsultValidator implements ConsultValidator {
    private final int minTimeToScheduleInMinutes = ValidatorsData.getMinTimeToScheduleInMinutes();
    public void validate(ConsultReserveData consultReserveData){
        var consultDate = consultReserveData.consultDate();
        var now = LocalDateTime.now();
        var timeDifferenceInMinutes = Duration.between(now, consultDate).toMinutes();
        if(timeDifferenceInMinutes < minTimeToScheduleInMinutes){
            throw new MedvollValidationException("Horario seleccionado con menos de " + minTimeToScheduleInMinutes + " minutos de anticipación");
        }
    }
}
