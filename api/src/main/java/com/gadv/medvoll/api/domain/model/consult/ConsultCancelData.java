package com.gadv.medvoll.api.domain.model.consult;

import jakarta.validation.constraints.NotNull;

public record ConsultCancelData(
        @NotNull
        Long consultId,
        @NotNull
        CancelReason cancelReason
) {
}
