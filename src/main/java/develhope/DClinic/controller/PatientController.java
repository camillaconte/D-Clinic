package develhope.DClinic.controller;


import develhope.DClinic.entities.PatientEntities;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")

public class PatientController {
    PatientEntities patient;

    @PostMapping
    public PatientEntities newPatient(@Validated @RequestBody PatientEntities patient ){

        return patient;
    }
    @GetMapping
    public String getPatient(){
        return patient.toString();
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id){
        return patient;
    }
}
