package com.gadv.medvoll.api.domain.model.consult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gadv.medvoll.api.domain.model.doctor.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultReserveData(
        Long idDoctor,
        @NotNull
        Long idPatient,
        @NotNull
        @Future
        //@JsonFormat(pattern = "dd/MM/yyyy HH:mm") //Ejemplo de formato de fecha
        LocalDateTime consultDate,
        Specialty specialty
) {
}
