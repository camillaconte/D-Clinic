package develhope.DClinic.service;

import develhope.DClinic.domain.Patient;
import develhope.DClinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }


    public void insertNewPatient(Patient patient) {
        Optional<Patient> optionalPatient = patientRepository.findPatientByEmail(patient.getEmail());
        if(optionalPatient.isPresent()){
            throw new IllegalStateException("email already taken");
        }
        patientRepository.save(patient);
    }

    public void deletePatientById(long patientId) {
        boolean exists = patientRepository.existsById(patientId);
        if(!exists){
            throw new IllegalStateException(
                    "patient with id " + patientId + " does not exists");
        }
        patientRepository.deleteById(patientId);
    }

    public void updatePatient(long patientId, String newEmail, String newName) {
        Optional<Patient> patientToFind = patientRepository.findById(patientId);
        if (patientToFind.isPresent()){
            Patient patient = patientToFind.get();
            patient.setEmail(newEmail);
            patient.setFirstName(newName);
            patientRepository.save(patient);
        } else throw new IllegalStateException("patient with this id not found");
    }
}
