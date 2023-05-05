package develhope.DClinic.service;

import develhope.DClinic.domain.DTO.PatientDTO;
import develhope.DClinic.domain.Entities.Patient;
import develhope.DClinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;



    public List<Patient> findAll() {
        return patientRepository.findAll();
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
    public PatientDTO getById(long patientId){
       PatientDTO responseDTO = new PatientDTO();
        Patient entity = patientRepository.getById(patientId);
        responseDTO.setFirstName(entity.getFirstName());
        responseDTO.setLastName(entity.getLastName());
        responseDTO.setFiscalCode(entity.getFiscalCode());
        responseDTO.setEmail(entity.getEmail());
        responseDTO.setPhoneNumber(entity.getPhoneNumber());
        responseDTO.setDateOfBirth(entity.getDateOfBirth());
        responseDTO.setAddress(entity.getAddress());
        return responseDTO;
    }
    public Patient insertPatient(PatientDTO dto){
        Patient entity = new Patient();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setFiscalCode(dto.getFiscalCode());
        entity.setEmail(dto.getEmail());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        patientRepository.save(entity);
        return entity;
    }
    public Patient updatePatient(long patientId, PatientDTO dto){
        Patient update = patientRepository.getById(patientId);
        if(dto.getFirstName() != null) update.setFirstName(dto.getFirstName());
        if(dto.getLastName() != null) update.setLastName(dto.getLastName());
        if(dto.getFiscalCode() != null) update.setFiscalCode(dto.getFiscalCode());
        if(dto.getEmail() != null) update.setEmail(dto.getEmail());
        if (dto.getDateOfBirth() != null) update.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getPhoneNumber() != null) update.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getAddress() != null) update.setAddress(dto.getAddress());
        patientRepository.saveAndFlush(update);
        return update;
    }
}
