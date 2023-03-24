package develhope.DClinic.controller;


import develhope.DClinic.entities.Patient;
import develhope.DClinic.repositories.PatientRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patient")

public class PatientController {
    @Autowired
    PatientRepositories patientRepositories;


    @PostMapping
    public String newPatient(@Validated @RequestBody Patient patient ){
        patientRepositories.save(patient);
        return "created patient";
    }
    @GetMapping
    public List<Patient> allPatient(){
     return patientRepositories.findAll();

    }

    @GetMapping("/{id}")
    public String getSinglePatient(@RequestBody Patient patient){
        return patientRepositories.toString();
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable long id){
        patientRepositories.deleteById(id);

    }


}
