package develhope.DClinic.service;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestDTO;
import develhope.DClinic.repository.LabTestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LabTestService   {

    @Autowired
    private LabTestRepository labTestRepository;

    public LabTestDTO save(LabTestDTO u){
        return (LabTestDTO) labTestRepository.save(u);
    }

    public void deleteByID(long id_test){
        labTestRepository.deleteById(id_test);
    }

    public Optional<LabTestDTO> getByID (long id_test){
        return labTestRepository.findById(id_test);
    }


}
