package develhope.DClinic.controller;

import develhope.DClinic.domain.DoctorRequestDTO;
import develhope.DClinic.domain.Patient;
import develhope.DClinic.service.DoctorService;
import develhope.DClinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("d_clinic/doctor")
class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/insert-patient")
    public void insertNewPatient(@RequestBody DoctorRequestDTO dto){
        try {

        }
    }



    @GetMapping("/get-all-patients")
    public List<Patient> getAllPatients (){
        //return List.of(); --> lista vuota
        return patientService.findAll();
    }



    @DeleteMapping("/delete-patient-by-id/{patientId}")
    public void deletePatientById(@PathVariable ("patientId") Integer patientId){
        patientService.deletePatientById(patientId);
    }

    @PutMapping("/update-patient/{patientId}")
    public void updatePatient(@PathVariable Integer patientId,@RequestParam String newEmail, String newName){
        patientService.updatePatient(patientId, newEmail, newName);
    }
}
