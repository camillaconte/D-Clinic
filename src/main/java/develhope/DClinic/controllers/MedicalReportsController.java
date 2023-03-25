package develhope.DClinic.controllers;

import develhope.DClinic.domain.MedicalReport;
import develhope.DClinic.domain.Patient;
import develhope.DClinic.services.MedicalReportService;
import develhope.DClinic.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalReportsController {
    MedicalReportService medicalReportService;
    PatientService patientService;

    @Autowired
    public MedicalReportsController(MedicalReportService medicalReportService, PatientService patientService){
        this.medicalReportService = medicalReportService;
        this.patientService = patientService;
    }

    /*@Autowired
    public MedicalReportsController(PatientService patientService){
        this.patientService = patientService;
    }*/

    @GetMapping("/get-all-reports-by-patientId/{patientId}")
    public List<MedicalReport> getAllReportsByPatient(@PathVariable ("patientId") Integer patientId){
        return patientService.findAllMedicalRecords(patientId);
    }

    @PostMapping("/insert-new-record")
    public void insertNewReport(@RequestBody MedicalReport report){
        medicalReportService.createNewReport(report);
    }


    @PostMapping("/add-report-response-entity")
    public ResponseEntity addReport(@RequestBody MedicalReport report) {
        try {
            medicalReportService.createNewReport(report);
            return ResponseEntity.ok(report);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


    @PutMapping("/update-record-history/{medicalRecordName}")
    public void updateReportHistory(@PathVariable ("medicalRecordName") String medicalRecordName,
                                    @RequestParam String history){
        medicalReportService.updateRecordHistory(medicalRecordName, history);
    }


    /*in questo caso potrei inserire un medicalreport vuoto come RequestBody?
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
            medicalReportService.getAllPatientRecords(patient);
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
