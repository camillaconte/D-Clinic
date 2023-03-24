package develhope.DClinic.controller;


import develhope.DClinic.entities.Patient;
import develhope.DClinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")

public class PatientController {
    @Autowired
    PatientService patientService;


    @PostMapping
    public String newPatient(@Validated @RequestBody Patient patient ){
       patientService.newPatient(patient);
        return "created patient";
    }
    @GetMapping
    public List<Patient> allPatient(){
     return patientService.allPatient();

    }

    @GetMapping("/{id}")
    public Patient getSinglePatient(@PathVariable long id){
        return patientService.getByID(id);
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable long id){
        patientService.deletePatient(id);

    }


}
