package develhope.DClinic.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import develhope.DClinic.domain.MedicalReport;
import develhope.DClinic.domain.MedicalReportDTO;
import develhope.DClinic.domain.Patient;
import develhope.DClinic.exceptions.DoctorNotFoundException;
import develhope.DClinic.exceptions.MedicalReportNameNotFoundException;
import develhope.DClinic.exceptions.PatientNotFoundException;
import develhope.DClinic.service.MedicalReportService;
import develhope.DClinic.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Controller for Medical Report (= "referti medici")
 * @author camillaconte
 *
 */
@RestController
@RequestMapping("/medical-report")
public class MedicalReportsController {

   @Autowired
   MedicalReportService medicalReportService;

   @Autowired
   PatientService patientService;

   Logger log = LoggerFactory.getLogger(MedicalReportsController.class);

    @PostMapping("/create-new-report")
    public ResponseEntity createReportResponse(@RequestBody MedicalReportDTO medicalReportDTO) {
        try {
            log.info("Trying to add new medical report " + medicalReportDTO.getReportName());
            medicalReportService.createNewReport(medicalReportDTO);
            log.info("Added new medical report for patient with id " + medicalReportDTO.getPatientId());
            return ResponseEntity.status(HttpStatus.CREATED).body("New Medical Report added!");
        } catch (PatientNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MedicalReportNameNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DoctorNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/get-all-reports-by-doctorId/{doctorId}")
    public ResponseEntity getAllReportsByDoctorId(@PathVariable ("doctorId") long doctorId) {
        try {
            //medicalReportService.getAllReportsByDoctorId(doctorId);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicalReportService.getAllReportsByDoctorId(doctorId));
        } catch (DoctorNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /*@GetMapping("/get-all-reports-by-patientId/{patientId}")
    public Set<MedicalReport> getAllReportsByPatientId(@PathVariable ("patientId") int patientId){
        return medicalReportService.getAllReportsByDoctorId
    }*/


    /**
     * Una funzionalità che permette di risalire
     * all'ultima history = anamnesi remota del paziente
     * così il medico che fa una nuova visita
     * può copiarla nel nuovo referto
     * senza stare a riscrivere tutto!
     */
    /*@GetMapping("/get-last-history-by-patientId/{patientId}")
    public String getLastHistory(){
        return medicalReportService.getLastHistory();
    }*/

    /**
     * TODO prevedere funzione che permetta di aggiornare
     * un MedicalReport già esistente!
     */

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
    /*@GetMapping("/get-all-patient-records")
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

}
