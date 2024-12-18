package com.gadv.medvoll.api.repository;

import com.gadv.medvoll.api.domain.model.consult.Consult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
    boolean existsByPatientIdAndConsultDateBetween(Long PatientId, LocalDateTime firstSchedule, LocalDateTime lastSchedule);

    boolean existsByDoctorIdAndConsultDateAndCancelReasonIsNull(Long doctorId, LocalDateTime consultDate);
}
