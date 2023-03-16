package develhope.DClinic.controllers;

import develhope.DClinic.domain.MedicalRecord;
import develhope.DClinic.domain.Patient;
import develhope.DClinic.services.MedicalRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalRecordsController {

    @Autowired
    MedicalRecordsService medicalRecordsService;


    //in questo caso potrei inserire un medicalrecord vuoto come RequestBody?
    @PostMapping("/create-new-record-for-patient")
    public ResponseEntity createNewRecord (@RequestParam String name, @RequestBody Patient patient) {
        try {
            medicalRecordsService.createNewRecord(name, patient);
            return ResponseEntity.ok(patient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /*@PostMapping("/add-patient-to-MedicalRecord")
    public ResponseEntity addMedicalRecord(@RequestBody ,@RequestParam String courseName) {
        try {
            studentService.setCourse(student, courseName);
            return ResponseEntity.ok(student);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }*/

    @GetMapping("/get-patient-records")
    public ResponseEntity getAllPatientsRecords(@RequestBody Patient patient) {
        try{
            medicalRecordsService.getAllPatientRecords(patient);
            return ResponseEntity.ok(patient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/set-history")
    public ResponseEntity setHistory(@RequestParam String name, @RequestParam String history){
        try{
            medicalRecordsService.setHistory(name, history);
            return ResponseEntity.ok(name);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public void getHistory(){}


}
