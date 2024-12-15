package com.gadv.medvoll.api.domain.model.consult;

import com.gadv.medvoll.api.domain.MedvollValidationException;
import com.gadv.medvoll.api.domain.model.consult.validations.ConsultValidator;
import com.gadv.medvoll.api.domain.model.doctor.Doctor;
import com.gadv.medvoll.api.domain.model.patient.Patient;
import com.gadv.medvoll.api.repository.ConsultRepository;
import com.gadv.medvoll.api.repository.DoctorRepository;
import com.gadv.medvoll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultReservation {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ConsultRepository consultRepository;

    @Autowired //llena la lista de todas la clases que implementan la interfaz ConsultValidator
    private List<ConsultValidator> validators;

    public void reserve(ConsultReserveData consultReserveData){
        if(!patientRepository.existsById(consultReserveData.idPatient())){
            throw new MedvollValidationException("No existe un paciente con el id proporcionado");
        }
        if(consultReserveData.idDoctor() != null && !doctorRepository.existsById(consultReserveData.idDoctor())){
            throw new MedvollValidationException("No existe un Doctor con el id proporcionado");
        }
        //validate
        validators.forEach(consultValidator -> consultValidator.validate(consultReserveData));

        var doctor = asignDoctor(consultReserveData);//doctorRepository.findById(consultReserveData.idDoctor()).get();//.getReferenceById(consultReserveData.idDoctor());
        var patient = patientRepository.findById(consultReserveData.idPatient()).get();//.getReferenceById(consultReserveData.idPatient());
        var consult = new Consult(null, doctor, patient, consultReserveData.consultDate(), null);
        consultRepository.save(consult);
    }

    private Doctor asignDoctor(ConsultReserveData consultReserveData) {
        if(consultReserveData.idDoctor() != null) {
            return doctorRepository.getReferenceById(consultReserveData.idDoctor());
        }
        if(consultReserveData.specialty() == null){
            throw new MedvollValidationException("Es necesario escoger una especialidad, cuando no se elige a un doctor.");
        }
        return doctorRepository.getRandomDoctorBySpecialtyAndConsultDate(consultReserveData.specialty(), consultReserveData.consultDate());
    }

    public void cancelConsult(ConsultCancelData consultCancelData) {
        if (!consultRepository.existsById(consultCancelData.consultId())) {
            throw new MedvollValidationException("Id de la consulta proporcionado no existe!");
        }
        var consult = consultRepository.getReferenceById(consultCancelData.consultId());
        consult.cancel(consultCancelData.cancelReason());
    }
}
