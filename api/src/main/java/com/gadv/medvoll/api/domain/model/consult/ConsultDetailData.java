package com.gadv.medvoll.api.domain.model.consult;

import java.time.LocalDateTime;

public record ConsultDetailData(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime consultDate
) {
}
