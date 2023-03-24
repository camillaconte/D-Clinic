package develhope.DClinic.controller;


import develhope.DClinic.entities.Patient;
import develhope.DClinic.repositories.PatientRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/patient")

public class PatientController {
    @Autowired
    PatientRepositories patientRepositories;

    ArrayList<Patient> listPatient=new ArrayList<>();
    @PostMapping
    public String newPatient(@Validated @RequestBody Patient patient ){
         listPatient.add(patient);
       return "created a new patient";
    }
    @GetMapping
    public String allPatient(){
        return listPatient.toString();
    }

    @GetMapping("/{id}")
    public String getSinglePatient(@RequestBody Patient patient){
        return patient.toString();
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable long id){
        patientRepositories.deleteById(id);

    }


}
