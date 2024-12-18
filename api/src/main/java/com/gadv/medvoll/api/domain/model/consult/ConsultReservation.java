package com.gadv.medvoll.api.domain.model.consult;

import com.gadv.medvoll.api.domain.MedvollValidationException;
import com.gadv.medvoll.api.domain.model.consult.validations.cancel.CancelConsultValidator;
import com.gadv.medvoll.api.domain.model.consult.validations.reserve.ConsultValidator;
import com.gadv.medvoll.api.domain.model.doctor.Doctor;
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
    private List<ConsultValidator> consultValidators;
    @Autowired
    private List<CancelConsultValidator> cancelConsultValidators;

    public ConsultDetailData reserve(ConsultReserveData consultReserveData){
        if(!patientRepository.existsById(consultReserveData.idPatient())){
            throw new MedvollValidationException("No existe un paciente con el id proporcionado");
        }
        if(consultReserveData.idDoctor() != null && !doctorRepository.existsById(consultReserveData.idDoctor())){
            throw new MedvollValidationException("No existe un Doctor con el id proporcionado");
        }

        //validate
        consultValidators.forEach(consultValidator -> consultValidator.validate(consultReserveData));

        var doctor = asignDoctor(consultReserveData);//doctorRepository.findById(consultReserveData.idDoctor()).get();//.getReferenceById(consultReserveData.idDoctor());
        if(doctor == null){
            throw new MedvollValidationException("No existe un Doctor disponible en ese horario");
        }
        var patient = patientRepository.findById(consultReserveData.idPatient()).get();//.getReferenceById(consultReserveData.idPatient());
        var consult = new Consult(null, doctor, patient, consultReserveData.consultDate(), null);
        consultRepository.save(consult);
        return new ConsultDetailData(consult);
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

        //validate
        cancelConsultValidators.forEach(consultValidator -> consultValidator.validate(consultCancelData));

        var consult = consultRepository.getReferenceById(consultCancelData.consultId());
        consult.cancel(consultCancelData.cancelReason());
    }
}
