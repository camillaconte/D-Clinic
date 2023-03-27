package develhope.DClinic.service;

import develhope.DClinic.domain.AppointmentDTo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;

@Service
public class CheckEmptyField {
    public HashSet<String> checkEmptyFieldNewLabTest(AppointmentDTo app) {
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
