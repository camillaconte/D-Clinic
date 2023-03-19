package develhope.DClinic.controllers;

import develhope.DClinic.domain.Patient;
import develhope.DClinic.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    PatientService patientService;

    @Autowired
    public PatientController (PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/get-all-patients")
    public List<Patient> getAllPatients (){
        //return List.of(); --> lista vuota
        return patientService.findAll();
    }

    @PostMapping("/insert-patient")
    public void insertNewPatient(@RequestBody Patient patient){
        patientService.insertNewPatient(patient);
    }

    @DeleteMapping("/delete-patient-by-id/{patientId}")
    public void deletePatientById(@PathVariable ("patientId") Integer patientId){
        patientService.deletePatientById(patientId);
    }

    @PutMapping("/update-patient/{patientId}")
    public void updatePatient(@PathVariable Integer patientId,@RequestParam String newEmail, String newName){
        patientService.updatePatient(patientId, newEmail, newName);
    }


}
