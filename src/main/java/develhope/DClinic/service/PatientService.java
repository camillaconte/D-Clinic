package develhope.DClinic.service;

import develhope.DClinic.domain.*;
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
        Patient entity =patientRepository.getById(patientId);
        responseDTO.setName(entity.getFirstName());
        responseDTO.setSurname(entity.getLastName());
        responseDTO.setFiscalCode(entity.getFiscalCode());
        responseDTO.setEmail(entity.getEmail());
        responseDTO.setPhoneNumber(entity.getPhoneNumber());
        responseDTO.setAge(entity.getAge());
        responseDTO.setAddress(entity.getAddress());
        return responseDTO;
    }
    public Patient insertPatient(PatientDTO dto){
        Patient entity = new Patient();
        entity.setFirstName(dto.getName());
        entity.setLastName(dto.getSurname());
        entity.setFiscalCode(dto.getFiscalCode());
        entity.setEmail(dto.getEmail());
        entity.setAge(dto.getAge());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        patientRepository.save(entity);
        return entity;
    }
    public Patient updatePatient(long patientId, PatientDTO dto){
        Patient update = patientRepository.getById(patientId);
        if(dto.getName() != null) update.setFirstName(dto.getName());
        if(dto.getSurname() != null) update.setLastName(dto.getSurname());
        if(dto.getFiscalCode() != null) update.setFiscalCode(dto.getFiscalCode());
        if(dto.getEmail() != null) update.setEmail(dto.getEmail());
        if (dto.getAge() != null) update.setAge(dto.getAge());
        if (dto.getPhoneNumber() != null) update.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getAddress() != null) update.setAddress(dto.getAddress());
        patientRepository.saveAndFlush(update);
        return update;
    }
}
