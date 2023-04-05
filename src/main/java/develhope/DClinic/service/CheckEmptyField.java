package develhope.DClinic.service;

import develhope.DClinic.domain.AppointmentDTo;
import develhope.DClinic.domain.LabTestRequestDTO;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.HashSet;

/**
 * @author Luca Giorgi
 * Classe check campi vuoti
 */
@Service
public class CheckEmptyField {
    public HashSet<String> checkEmptyFieldNewLabTest(LabTestRequestDTO u){
        HashSet<String> MESSAGE_ERROR = new HashSet<>();
        if(u.getFiscalCode() == null){
            MESSAGE_ERROR.add("FISCAL CODE NOT INSERT");
        }
        if(u.getValue() == 0){
            MESSAGE_ERROR.add("VALUE NOT INSERT");
        }
        if(u.getDate() == null){
            u.setDate(LocalDate.now());
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

