package develhope.DClinic.service;

import develhope.DClinic.domain.Patient;
import develhope.DClinic.domain.PatientDTO;
import develhope.DClinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public Patient insertNewPatient(PatientDTO dto){
        Patient entity = new Patient();
        entity.setFirstName(dto.getName());
        entity.setLastName(dto.getSurname());
        entity.setFiscalCode(dto.getFiscalCode());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        patientRepository.save(entity);
        return entity;
    }

    public void deletePatientByFiscalCode(String fiscalCode){
        long id = patientRepository.getByFiscalCode(fiscalCode).getId();
        patientRepository.deleteById(id);
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

    public Patient updatePatient(String fiscalCode, PatientDTO patient) {
        Optional<Patient> patientToFind = patientRepository.findPatientByFiscalCode(fiscalCode);

        if (patientToFind.isPresent()){
            patientRepository.save(patientToFind.get());
        } else throw new IllegalStateException("patient with this is not found");
        return patientToFind.get();
    }
}
