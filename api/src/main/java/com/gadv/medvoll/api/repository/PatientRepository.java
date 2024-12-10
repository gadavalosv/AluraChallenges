package com.gadv.medvoll.api.repository;

import com.gadv.medvoll.api.domain.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByActiveTrue(Pageable pagination);
}
