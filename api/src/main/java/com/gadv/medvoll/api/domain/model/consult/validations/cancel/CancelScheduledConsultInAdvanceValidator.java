package com.gadv.medvoll.api.domain.model.consult.validations.cancel;

import com.gadv.medvoll.api.domain.MedvollValidationException;
import com.gadv.medvoll.api.domain.model.consult.ConsultCancelData;
import com.gadv.medvoll.api.domain.model.consult.validations.data.CancelValidatorsData;
import com.gadv.medvoll.api.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancelScheduledConsultInAdvanceValidator implements CancelConsultValidator{
    @Autowired
    private ConsultRepository consultRepository;
    private final int timeInAdvanceToCancelInHours = CancelValidatorsData.getTimeInAdvanceToCancelInHours();
    @Override
    public void validate(ConsultCancelData consultCancelData) {
        var consult = consultRepository.getReferenceById(consultCancelData.consultId());
        var now = LocalDateTime.now();
        var timeDifferenceInHours = Duration.between(now, consult.getConsultDate()).toHours();
        if(timeDifferenceInHours < timeInAdvanceToCancelInHours){
            throw new MedvollValidationException("¡La consulta solo puede ser cancelada con anticipación mínima de 24 horas!");
        }
    }
}
