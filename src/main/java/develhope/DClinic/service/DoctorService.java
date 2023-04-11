package develhope.DClinic.service;

import develhope.DClinic.domain.Doctor;
import develhope.DClinic.domain.DoctorRequestDTO;
import develhope.DClinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public Doctor insertNewDoctor(DoctorRequestDTO dto){
        Doctor entity = new Doctor();
        entity.setFirstname(dto.getFirstName());
        entity.setLastname(dto.getLastName());
        entity.setFiscalCode(dto.getFiscalCode());
        entity.setEmail(entity.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setTelephoneNumber(dto.getTelephoneNumber());
        entity.setSpecialization(dto.getSpecialization());
        doctorRepository.save(entity);
        return entity;
    }




}
