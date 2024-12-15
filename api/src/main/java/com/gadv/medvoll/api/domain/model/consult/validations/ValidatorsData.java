package com.gadv.medvoll.api.domain.model.consult.validations;

import lombok.Getter;

public class ValidatorsData {
    @Getter
    private static final int
            consultDurationInHours = 1,
            timeClinicOpens = 7,
            timeClinicCloses = 19,
            minTimeToScheduleInMinutes = 30;
}
