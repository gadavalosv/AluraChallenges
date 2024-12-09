package com.gadv.medvoll.api.repository;

import com.gadv.medvoll.api.domain.model.doctor.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByActiveTrue(Pageable pagination);
}
