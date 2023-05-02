package develhope.DClinic.controller;

import develhope.DClinic.domain.*;
import develhope.DClinic.service.CheckEmptyField;
import develhope.DClinic.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    PatientService patientService;
    private CheckEmptyField checkEmptyField;

    public PatientController (PatientService patientService){
        this.patientService = patientService;
        //this.checkEmptyField = checkEmptyField;
    }


    @PostMapping
    public ResponseEntity insetNewPatient(@RequestBody PatientDTO dto){
                Patient patient = patientService.insertNewPatient(dto);
                return ResponseEntity.status(HttpStatus.OK).body(patient);
    }


    @DeleteMapping("/{fiscalCode}")
    public ResponseEntity deleteByFiscalCode(@PathVariable String fiscalCode){
            return ResponseEntity.status(HttpStatus.OK).body(patientService.deletePatientById(fiscalCode); );
    }

    @PutMapping("/{fiscalCode}")
    public ResponseEntity update(@PathVariable String fiscalCode, @RequestBody PatientDTO dto){
            Patient update = patientService.updatePatient(fiscalCode, dto);
            return ResponseEntity.ok(update);
    }

    @GetMapping("/{fiscalCode}")
    public ResponseEntity getPatientByFiscalCode(@PathVariable String fiscalCode){
            PatientDTO patient = patientService.getByFiscalCode(fiscalCode);
            return ResponseEntity.ok(patient);

    }

    @GetMapping("/")
    public ResponseEntity getAllDoctors(){
        try{
            List<DoctorResponseDTO> responseDTOList = patientService.getAllPatient();
            return ResponseEntity.ok(responseDTOList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
