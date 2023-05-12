package develhope.DClinic.user.service;

import develhope.DClinic.security.auth.AuthenticationService;
import develhope.DClinic.user.domain.dto.DoctorRequestDTO;
import develhope.DClinic.user.domain.dto.DoctorResponseDTO;
import develhope.DClinic.user.domain.entities.Doctor;
import develhope.DClinic.user.domain.entities.User;
import develhope.DClinic.user.repositories.DoctorRepository;
import jakarta.servlet.http.HttpServletRequest;
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
    @Autowired
    AuthenticationService authenticationService;

    public Doctor insertNewDoctorSV(DoctorRequestDTO dto /*HttpServletRequest request*/){
        Doctor entity = new Doctor();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setFiscalCode(dto.getFiscalCode());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhoneNumber(dto.getTelephoneNumber());
        entity.setSpecialization(dto.getSpecialization());
        entity.setAddress(dto.getAddress());
        entity.setDateOfBirth(dto.getDateOfBirth());
        /*User user = authenticationService.userByToken(request);
        entity.setUser(user);*/
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
        responseDTO.setAddress(entity.getAddress());
        responseDTO.setDateOfBirth(entity.getDateOfBirth());
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
        if(dto.getAddress() != null) update.setAddress(dto.getAddress());
        if(dto.getDateOfBirth() != null) update.setDateOfBirth(dto.getDateOfBirth());
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
            dto.setAddress(x.getAddress());
            dto.setDateOfBirth(x.getDateOfBirth());
            dto.setTelephoneNumber(x.getPhoneNumber());
            dto.setSpecialization(x.getSpecialization());
            listOutput.add(dto);
        }
        return listOutput;
    }
}
