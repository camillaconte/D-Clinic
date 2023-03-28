package develhope.DClinic.service;

import develhope.DClinic.domain.AppointmentDTo;
import develhope.DClinic.domain.LabTestDTO;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * @author Luca Giorgi
 * Classe check campi vuoti
 */
@Service
public class CheckEmptyField {
    public HashSet<String> checkEmptyFieldNewLabTest(LabTestDTO u){
        HashSet<String> MESSAGE_ERROR = new HashSet<>();
        if(u.getPatient() == null){
            MESSAGE_ERROR.add("PATIENT NOT INSERT");
        }
        if(u.getDescription() == null){
            MESSAGE_ERROR.add("DESCRIPTION NOT INSERT");
        }
        if(u.getLabParameter() == null){
            MESSAGE_ERROR.add("PARAM NOT INSERT");
        }
        if(u.getDate() == null){
            u.setDate(LocalDateTime.now());
        }
        return MESSAGE_ERROR;
    }

    public HashSet<String> checkEmptyFieldNewAppointment(AppointmentDTo app) {
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
    }
   

}

