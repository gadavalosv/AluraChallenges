package com.gadv.medvoll.api.domain.model.consult.validations.cancel;

import com.gadv.medvoll.api.domain.model.consult.ConsultCancelData;

public interface CancelConsultValidator {
    void validate(ConsultCancelData consultCancelData);
}
