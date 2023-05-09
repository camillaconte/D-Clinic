package develhope.DClinic.user.controllers;

import develhope.DClinic.user.domain.dto.PatientDTO;
import develhope.DClinic.user.domain.entities.Patient;
import develhope.DClinic.utils.CheckEmptyField;
import develhope.DClinic.user.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;
    @Autowired
    CheckEmptyField checkEmptyField;

    private Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    @PostMapping("/insert-patient")
    public ResponseEntity insertPatient(@RequestBody PatientDTO dto) {
        HashSet<String> error = checkEmptyField.checkEmptyFieldPatient(dto);
        if (error.isEmpty()) {
            try {
                Patient patient = patientService.insertPatient(dto);
                return ResponseEntity.ok(patient);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @GetMapping("/get-all-patients")
    public ResponseEntity getAllPatient() {
        try {
            List<Patient> responseList = patientService.findAll();
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{patientId}")
    public ResponseEntity getPatientById(@PathVariable long patientId) {
        try {
            PatientDTO output = patientService.getById(patientId);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            LOGGER.error("The patient does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-patient-by-id/{patientId}")
    public ResponseEntity deleteById(@PathVariable Integer patientId) {
        try {
            patientService.deletePatientById(patientId);
            return ResponseEntity.ok("Patient with id " + patientId + " has been successfully deleted");
        } catch (Exception e) {
            LOGGER.error("The patient does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{patientId}")
    public ResponseEntity update(@PathVariable long patientId, @RequestBody PatientDTO dto) {
        try {
            Patient update = patientService.updatePatient(patientId, dto);
            return ResponseEntity.ok(update);
        } catch (Exception e) {
            LOGGER.error("The patient to be modified does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}




