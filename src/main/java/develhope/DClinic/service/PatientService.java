package develhope.DClinic.service;

import develhope.DClinic.domain.Patient;
import develhope.DClinic.repository.PatientRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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

