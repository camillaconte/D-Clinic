package develhope.DClinic.service;



import develhope.DClinic.domain.AppointmentDTo;
import develhope.DClinic.domain.LabTestRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * @author Luca Giorgi
 * Classe check dei campi vuoti
 */
@Service
public class CheckEmptyField {
    public HashSet<String> checkEmptyFieldNewLabTest(LabTestRequestDTO u){
        HashSet<String> MESSAGE_ERROR = new HashSet<>();
        if(u.getFiscalCode() == null){
            MESSAGE_ERROR.add("FISCAL CODE NOT INSERT");
        }
        return MESSAGE_ERROR;
    }

    /*public HashSet<String> checkEmptyFieldNewAppointment(AppointmentDTO app) {
        HashSet<String> MESSAGE_ERROR = new HashSet<>();
        if (app.getPatient() == null) {
            MESSAGE_ERROR.add("PATIENT NOT INSERT");
        }
        if (app.getDoctor() == null) {
            MESSAGE_ERROR.add("DOCTOR NOT INSERT");
        }
        if (app.getClinic() == null) {
            MESSAGE_ERROR.add("CLINIC NOT INSERT");
        }
        if (app.getDate() == null) {
            app.setDate(LocalDate.now());
        }
        return MESSAGE_ERROR;
    }*/


    public HashSet<String> checkEmptyFieldNewDoctor(DoctorRequestDTO dto) {
        HashSet<String> MESSAGE_ERROR = new HashSet<>();
        if(dto.getFirstName() == null) MESSAGE_ERROR.add("FIRST NAME IS NOT INSERT");
        if (dto.getLastName() == null) MESSAGE_ERROR.add("LAST NAME IS NOT INSERT");
        if (dto.getFiscalCode() == null) MESSAGE_ERROR.add("FISCAL CODE IS NOT INSERT");
        if (dto.getEmail() == null) MESSAGE_ERROR.add("EMAIL IS NOT INSERT");
        if (dto.getTelephoneNumber() == null) MESSAGE_ERROR.add("TELEPHONE NUMBER IS NOT INSERT");
        if (dto.getPassword() == null) MESSAGE_ERROR.add("PASSWORD IS NOT INSERT");
        if (dto.getSpecialization() == null) MESSAGE_ERROR.add("SPECIALIZATION NOT INSERT");
        return MESSAGE_ERROR;
    }
   

}

