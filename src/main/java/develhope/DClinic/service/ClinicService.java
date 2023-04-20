package develhope.DClinic.service;

import develhope.DClinic.domain.Clinic;
import develhope.DClinic.domain.ClinicDTO;
import develhope.DClinic.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public Clinic createClinic(ClinicDTO clinicDTO){
        Clinic clinic = new Clinic();
        clinic.setName(clinicDTO.getName());
        clinic.setCity(clinicDTO.getCity());
        clinic.setDescription(clinicDTO.getDescription());
        clinicRepository.save(clinic);
        return clinic;
    }



}
