package develhope.DClinic.controllers;

import develhope.DClinic.domain.MedicalRecord;
import develhope.DClinic.domain.Patient;
import develhope.DClinic.repositories.PatientRepo;
import develhope.DClinic.services.MedicalRecordsService;
import develhope.DClinic.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordsController {
    MedicalRecordsService medicalRecordsService;
    PatientService patientService;

    @Autowired
    public MedicalRecordsController(MedicalRecordsService medicalRecordsService, PatientService patientService){
        this.medicalRecordsService = medicalRecordsService;
        this.patientService = patientService;
    }

    /*@Autowired
    public MedicalRecordsController(PatientService patientService){
        this.patientService = patientService;
    }*/

    @GetMapping("/get-all-records-by-patientId/{patientId}")
    public List<MedicalRecord> getAllRecordsByPatient(@PathVariable ("patientId") Integer patientId){
        return patientService.findAllMedicalRecords(patientId);
    }

    @PostMapping("/insert-new-record")
    public void insertNewRecord(@RequestBody MedicalRecord record){
        medicalRecordsService.createNewRecord(record);
    }

    @PutMapping("/update-record-history/{medicalRecordName}")
    public void updateRecordHistory(@PathVariable ("medicalRecordName") String medicalRecordName,
                                    @RequestParam String history){
        medicalRecordsService.updateRecordHistory(medicalRecordName, history);
    }


    /*in questo caso potrei inserire un medicalrecord vuoto come RequestBody?
    @PostMapping("/create-new-record-for-patient")
    public ResponseEntity createNewRecord (@RequestParam String name, @RequestBody Patient patient) {
        try {
            medicalRecordsService.createNewRecord(name, patient);
            return ResponseEntity.ok(patient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }*/

    /*@PostMapping("/add-patient-to-MedicalRecord")
    public ResponseEntity addMedicalRecord(@RequestBody ,@RequestParam String courseName) {
        try {
            studentService.setCourse(student, courseName);
            return ResponseEntity.ok(student);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }*/

    /**
     * Questo metodo andrebbe nel PatientController
     * @param patient
     * @return
     */
    @GetMapping("/get-all-patient-records")
    public ResponseEntity getAllPatientsRecords(@RequestBody Patient patient) {
        try{
            medicalRecordsService.getAllPatientRecords(patient);
            return ResponseEntity.ok(patient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /* Metodo sugggerito da Carlo per un altro problema?
    Con ResponseEntity!

    @PutMapping ("/set-history")
    public ResponseEntity setHistory(@RequestParam String name, @RequestParam String history){
        try{
            medicalRecordsService.setHistory(name, history);
            return ResponseEntity.ok(name);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }*/

    /**
     * Mi servirà un metodo per avere in qualunque parte del software
     * l'ultimo aggiornamento della storia clinica del paziente
     * magari scritto anche da un altro medico...
     * ...da usare per un nuovo referto...
     */
    @GetMapping
    public void getHistory(){}

}
