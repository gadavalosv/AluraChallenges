package com.gadv.medvoll.api.repository;

import com.gadv.medvoll.api.domain.model.address.AddressData;
import com.gadv.medvoll.api.domain.model.consult.Consult;
import com.gadv.medvoll.api.domain.model.doctor.Doctor;
import com.gadv.medvoll.api.domain.model.doctor.DoctorRegisterData;
import com.gadv.medvoll.api.domain.model.doctor.Specialty;
import com.gadv.medvoll.api.domain.model.patient.Patient;
import com.gadv.medvoll.api.domain.model.patient.PatientRegisterData;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Debería devolver null cuando el Doctor buscado existe pero no esta disponible en esa fecha")
    void getRandomDoctorBySpecialtyAndConsultDate_scene1() {
        //given o arrange
        var nextMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var doctor = registerDoctor("Doctor1", "doctor@gmail.com", "12345", Specialty.CARDIOLOGIA);
        var patient = registerPatient("Paciente1", "paciente@gmail.com", "123789");
        registerConsult(doctor, patient, nextMondayAt10);
        //when o act
        var availableDoctor = doctorRepository.getRandomDoctorBySpecialtyAndConsultDate(Specialty.CARDIOLOGIA, nextMondayAt10);
        //then o assert
        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Debería devolver Doctor cuando el Doctor buscado esta disponible en esa fecha")
    void getRandomDoctorBySpecialtyAndConsultDate_scene2() {
        //given o arrange
        var nextMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var doctor = registerDoctor("Doctor1", "doctor@gmail.com", "12345", Specialty.CARDIOLOGIA);
        //when o act
        var availableDoctor = doctorRepository.getRandomDoctorBySpecialtyAndConsultDate(Specialty.CARDIOLOGIA, nextMondayAt10);
        //then o assert
        assertThat(availableDoctor).isEqualTo(doctor);
    }

    private void registerConsult(Doctor doctor, Patient patient, LocalDateTime date) {
        entityManager.persist(new Consult(null, doctor, patient, date, null));
    }

    private Doctor registerDoctor(String name, String email, String document, Specialty specialty) {
        var doctor = new Doctor(doctorRegisterData(name, email, document, specialty));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String document) {
        var patient = new Patient(patientRegisterData(name, email, document));
        entityManager.persist(patient);
        return patient;
    }

    private DoctorRegisterData doctorRegisterData(String name, String email, String document, Specialty specialty) {
        return new DoctorRegisterData(
                name,
                email,
                document,
                "6145489789",
                specialty,
                addressData()
        );
    }

    private PatientRegisterData patientRegisterData(String name, String email, String document) {
        return new PatientRegisterData(
                name,
                email,
                document,
                "1234564",
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "calle x",
                "distrito y",
                "ciudad z",
                "123",
                "1"
        );
    }
}