package com.gadv.medvoll.api.repository;

import com.gadv.medvoll.api.domain.model.doctor.Doctor;
import com.gadv.medvoll.api.domain.model.doctor.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByActiveTrue(Pageable pagination);

    //POSTGRESQL
    @Query("""
            SELECT d FROM Doctor d
            WHERE
                d.active = true
                AND
                d.specialty = :specialty
                AND
                d.id NOT IN (
                    SELECT c.doctor.id FROM Consult c
                    WHERE
                        c.consultDate = :consultDate
                )
            ORDER BY RANDOM()
            LIMIT 1
            """)
    Doctor getRandomDoctorBySpecialtyAndConsultDate(Specialty specialty, LocalDateTime consultDate);

    boolean findActiveById(Long idDoctor);
}
