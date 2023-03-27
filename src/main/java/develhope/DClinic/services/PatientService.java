package develhope.DClinic.services;

import develhope.DClinic.domain.MedicalReport;
import develhope.DClinic.domain.Patient;
import develhope.DClinic.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    PatientRepo patientRepo;

    @Autowired
    public PatientService(PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }


    public void insertNewPatient(Patient patient) {
        Optional<Patient> optionalPatient = patientRepo.findPatientByEmail(patient.getEmail());
        if(optionalPatient.isPresent()){
            throw new IllegalStateException("email already taken");
        }
        patientRepo.save(patient);
    }

    public void deletePatientById(Integer patientId) {
        boolean exists = patientRepo.existsById(patientId);
        if(!exists){
            throw new IllegalStateException(
                    "patient with id " + patientId + " does not exists");
        }
        patientRepo.deleteById(patientId);
    }

    public void updatePatient(Integer patientId, String newEmail, String newName) {
        Optional<Patient> patientToFind = patientRepo.findById(patientId);
        if (patientToFind.isPresent()){
            Patient patient = patientToFind.get();
            patient.setEmail(newEmail);
            patient.setName(newName);
            patientRepo.save(patient);
        } else throw new IllegalStateException("patient with this id not found");
    }
}
