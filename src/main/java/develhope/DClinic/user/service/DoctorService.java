package develhope.DClinic.user.service;

import develhope.DClinic.user.domain.dto.DoctorRequestDTO;
import develhope.DClinic.user.domain.dto.DoctorResponseDTO;
import develhope.DClinic.user.domain.entities.Doctor;
import develhope.DClinic.user.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Luca Giorgi
 * Service per Doctor
 */

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public Doctor insertNewDoctorSV(DoctorRequestDTO dto){
        Doctor entity = new Doctor();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setFiscalCode(dto.getFiscalCode());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhoneNumber(dto.getTelephoneNumber());
        entity.setSpecialization(dto.getSpecialization());
        doctorRepository.save(entity);
        return entity;
    }

    public void deleteDoctorByFiscalCodeSV(String fiscalCode){
        long id = doctorRepository.getByFiscalCode(fiscalCode).getId();
        doctorRepository.deleteById(id);
    }

    public DoctorResponseDTO getByFiscalCodeSV(String fiscalCode){
        DoctorResponseDTO responseDTO = new DoctorResponseDTO();
        Doctor entity =doctorRepository.getByFiscalCode(fiscalCode);
        responseDTO.setId(entity.getId());
        responseDTO.setFirstName(entity.getFirstName());
        responseDTO.setLastName(entity.getLastName());
        responseDTO.setFiscalCode(entity.getFiscalCode());
        responseDTO.setEmail(entity.getEmail());
        responseDTO.setTelephoneNumber(entity.getPhoneNumber());
        responseDTO.setSpecialization(entity.getSpecialization());
        return responseDTO;
    }

    public Doctor updateDoctorSV(String fiscalCode, DoctorRequestDTO dto){
        Doctor update = doctorRepository.getByFiscalCode(fiscalCode);
        if(dto.getFirstName() != null) update.setFirstName(dto.getFirstName());
        if(dto.getLastName() != null) update.setLastName(dto.getLastName());
        if(dto.getFiscalCode() != null) update.setFiscalCode(dto.getFiscalCode());
        if(dto.getEmail() != null) update.setEmail(dto.getEmail());
        if (dto.getPassword() != null) update.setPassword(dto.getPassword());
        if (dto.getTelephoneNumber() != null) update.setPhoneNumber(dto.getTelephoneNumber());
        if (dto.getSpecialization() != null) update.setSpecialization(dto.getSpecialization());
        doctorRepository.saveAndFlush(update);
        return update;
    }

    public List<DoctorResponseDTO> getAllDoctorSV(){
        List<Doctor> listEntities = doctorRepository.findAll();
        List<DoctorResponseDTO> listOutput = new ArrayList<>();
        for(Doctor x : listEntities){
            DoctorResponseDTO dto = new DoctorResponseDTO();
            dto.setFirstName(x.getFirstName());
            dto.setLastName(x.getLastName());;
            dto.setFiscalCode(x.getFiscalCode());
            dto.setEmail(x.getEmail());
            dto.setTelephoneNumber(x.getPhoneNumber());
            dto.setSpecialization(x.getSpecialization());
            listOutput.add(dto);
        }
        return listOutput;
    }
}
