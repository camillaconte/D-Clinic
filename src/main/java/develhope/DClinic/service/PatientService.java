package develhope.DClinic.service;

import develhope.DClinic.entities.Patient;
import develhope.DClinic.repositories.PatientRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class PatientService {
    @Autowired
    PatientRepositories patientRepositories;
    public Patient getByID(long id){
        return patientRepositories.getById(id);
    }
    public List<Patient> allPatient(){
        return patientRepositories.findAll();
    }
    public void deletePatient(long id){
        patientRepositories.deleteById(id);
    }
    public Patient newPatient( Patient patient ){
      return  patientRepositories.save(patient);
    }

}

