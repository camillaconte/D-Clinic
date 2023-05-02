package develhope.DClinic.controller;

import develhope.DClinic.domain.MedicalReportDTO;
import develhope.DClinic.exceptions.DoctorNotFoundException;
import develhope.DClinic.exceptions.MedicalReportNameNotInsertedException;
import develhope.DClinic.exceptions.MedicalReportsNotFoundException;
import develhope.DClinic.exceptions.PatientNotFoundException;
import develhope.DClinic.service.MedicalReportService;
import develhope.DClinic.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Medical Report (= "referti medici")
 * @author camillaconte
 *
 */
@RestController
@RequestMapping("/medical-report")
public class MedicalReportController {

   @Autowired
   MedicalReportService medicalReportService;

   @Autowired
   PatientService patientService;

   Logger log = LoggerFactory.getLogger(MedicalReportController.class);

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
        } catch (MedicalReportNameNotInsertedException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DoctorNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //-----------------------------------------------------------------------------------------------------//
    @GetMapping("/get-all-reports-patient-fiscalCode")
    public ResponseEntity getAllPatientReportsByFiscalCode(@RequestParam String patientFiscalCode){
        try{
            return ResponseEntity.ok().body(medicalReportService.getAllPatientReportsByFiscalCode(patientFiscalCode));
        } catch (PatientNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MedicalReportsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //-----------------------------------------------------------------------------------------------------//
    @GetMapping("/get-all-reports-doctor/{doctorId}")
    public ResponseEntity getAllDoctorReportsByDoctorId(@PathVariable ("doctorId") long doctorId) {
        try {
            //medicalReportService.getAllReportsByDoctorId(doctorId);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicalReportService.getAllDoctorReportsByDoctorId(doctorId));
        } catch (DoctorNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //-----------------------------------------------------------------------------------------------------//
     @PutMapping ("/update-history-by-reportId/{medicalReportId}")
     public ResponseEntity updateHistory(@PathVariable long medicalReportId, @RequestParam String updatedHistory){
         try {
             return ResponseEntity.ok(medicalReportService.updateHistory(medicalReportId, updatedHistory));
         } catch (MedicalReportsNotFoundException e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
         }
     }

    //-----------------------------------------------------------------------------------------------------//

    /**
     * Funzione che permetta di risalire all'ultima history (anamnesi remota) del paziente
     * così il medico che fa una nuova visita può copiarla nel nuovo referto senza stare a riscrivere tutto!
     *
     * Fa uso di una CUSTOM QUERY nel MedicalReportRepository: TODO sistemare la Custom Query
     */

    @GetMapping("/find-last-patient-history/{patientId}")
    public ResponseEntity getLastPatientHistory(@PathVariable long patientId){
        try {
            return ResponseEntity.ok(medicalReportService.getLastPatientHistory(patientId));
        } catch (MedicalReportsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //-----------------------------------------------------------------------------------------------------//
    /**
     * TODO prevedere funzioni che permettano di aggiornare un MedicalReport già esistente
     */

    //-----------------------------------------------------------------------------------------------------//
    /**
     * TODO prevedere delete --> CASCADE???
    */

    //-----------------------------------------------------------------------------------------------------//

}
