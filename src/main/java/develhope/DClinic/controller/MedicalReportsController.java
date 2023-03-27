package develhope.DClinic.controller;

import develhope.DClinic.domain.MedicalReport;
import develhope.DClinic.domain.Patient;
import develhope.DClinic.service.MedicalReportService;
import develhope.DClinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalReportsController {

   @Autowired
   MedicalReportService medicalReportService;

   @Autowired
   PatientService patientService;


    /*@Autowired
    public MedicalReportsController(MedicalReportService medicalReportService, PatientService patientService){
        this.medicalReportService = medicalReportService;
        this.patientService = patientService;
    }*/

    @PostMapping("/create-new-report-response-entity")
    public ResponseEntity createReportResponse(@RequestBody MedicalReport report) {
        return medicalReportService.createReportResponse(report);
    }

    /* DA IMPLEMENTARE QUANDO AVRò I DOCTOR
    @GetMapping("/get-all-reports-by-doctorId/{doctorId}")
    public List<MedicalReport> getAllReportsByDoctorId(@PathVariable ("doctorId") long doctorId{
    medicalReportService.getALLReportsByDoctorId;
     */
    @GetMapping("/get-all-reports-by-patientId/{patientId}")
    public List<MedicalReport> getAllReportsByPatientId(@PathVariable ("patientId") int patientId){
        return medicalReportService.findAllReportsByPatientId(patientId);
    }

    @GetMapping("/get-last-history-by-patientId/{patientId}")
    public String getLastHistory(){
        return medicalReportService.getLastHistory();
    }

    /*@PutMapping("/update-record-history/{medicalRecordName}")
    public void updateReportHistory(@PathVariable ("medicalRecordName") String medicalRecordName,
                                    @RequestParam String history){
        medicalReportService.updateReportHistory(medicalRecordName, history);
    }*/


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
    public ResponseEntity addMedicalRecord(@RequestBody Patient patient, @RequestParam String ame) {
        try {
            Service.set(student, courseName);
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
    public ResponseEntity getAllPatientsReports(@RequestBody Patient patient) {
        try{
            medicalReportService.getAllPatientReports(patient);
            return ResponseEntity.ok(patient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /* Metodo sugggerito da Carlo per un altro problema
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
