package develhope.DClinic.booking.services;

import develhope.DClinic.booking.domain.entities.Clinic;
import develhope.DClinic.booking.domain.dto.ClinicDTO;
import develhope.DClinic.booking.repositories.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Clinic> getClinic(long clinicId)throws Exception{

        Optional<Clinic> clinic = clinicRepository.findById(clinicId);
         if (clinic.isEmpty()){
             throw new Exception("Clinic whit id: " + clinicId + "not found in database");
         }
         return clinic;
    }
    public List<Clinic> getClinicByCity(String city)throws Exception{

        List<Clinic> clinic = clinicRepository.findBycity(city);
        if (clinic.isEmpty()){
            throw new Exception("Clinics in: " + city + "not found in database");
        }
        return clinic;
    }




}
