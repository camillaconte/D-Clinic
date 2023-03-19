package develhope.DClinic.service;

import develhope.DClinic.domain.LabTestDTO;
import develhope.DClinic.repository.LabTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author Luca Giorgi
 * Servizi LabTest
 */
@Service
public class LabTestService   {

    @Autowired
    private LabTestRepository labTestRepository;
    @Autowired
    private CheckEmptyFieldOfLabTest checkEmptyFieldOfLabTest;

    public ResponseEntity insertNewTest(LabTestDTO labTestDTO){
        HashSet<String> MESSAGE_ERROR = checkEmptyFieldOfLabTest.checkEmptyFieldNewLabTest(labTestDTO);
        try{
            if(labTestDTO.getPatient() != null && labTestDTO.getResult() != null && labTestDTO.getDescription() != null){
                System.out.println("New laboratory test is insert");
                labTestRepository.save(labTestDTO);
                return new ResponseEntity<>(labTestDTO, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deleteByID(long id_test){
        try {
            labTestRepository.deleteById(id_test);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getByID (long id_test){
        try {
            Optional<LabTestDTO> labTestByID = labTestRepository.findById(id_test);
            return new ResponseEntity<>(labTestByID, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity update(long id, LabTestDTO labTestDTO){
        try{
            Optional<LabTestDTO> m = labTestRepository.findById(id);
            if(m.isPresent()){
                labTestRepository.deleteById(id);
                labTestRepository.save(labTestDTO);
                return new ResponseEntity<>(labTestDTO, HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
