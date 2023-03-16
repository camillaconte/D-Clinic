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

    /** Creazione esame nel db */
    public LabTestDTO save(LabTestDTO u){
        return (LabTestDTO) labTestRepository.save(u);
    }
    /**Emilinazione esame per il suo id */
    public void deleteByID(long id_test) throws Exception {
        if(!labTestRepository.existsById(id_test)){
            throw new Exception("Test not found,unable to delete");
        }
        labTestRepository.deleteById(id_test);
    }
    /** Cercare un esame per il suo id */
    public Optional<LabTestDTO> getByID (long id_test) throws Exception {
        if(!labTestRepository.existsById(id_test)){
            throw new Exception("Not Found");
        }
        return labTestRepository.findById(id_test);
    }

    /** Cercare tutti gli esami di un patiente */
    //public List<LabTestDTO> getAllByID (long fiscalCode){
    //    return labTestRepository.findAllById(fiscalCode);
    //}



}
