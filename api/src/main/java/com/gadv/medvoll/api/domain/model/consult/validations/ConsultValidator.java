package com.gadv.medvoll.api.domain.model.consult.validations;

import com.gadv.medvoll.api.domain.model.consult.ConsultReserveData;

public interface ConsultValidator {
    void validate(ConsultReserveData consultReserveData);
}
