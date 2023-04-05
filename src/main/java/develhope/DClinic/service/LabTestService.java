package develhope.DClinic.service;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestDTO;
import develhope.DClinic.mapper.LabTestMapper;
import develhope.DClinic.repository.LabTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * @author Luca Giorgi
 * Servizi LabTest
 */
@Service
public class LabTestService   {

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private LabTestMapper labTestMapper;
    @Autowired
    private CheckEmptyField checkEmptyField;

    public ResponseEntity insertNewTest(LabTestDTO labTestDTO){
        HashSet<String> MESSAGE_ERROR = checkEmptyField.checkEmptyFieldNewLabTest(labTestDTO);
        try{
            System.out.println("New laboratory test is insert");
            LabTest test = labTestRepository.save(labTestMapper.mapToLabTest(labTestDTO));
            return new ResponseEntity<>(test, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
            LabTestDTO labTestByID = labTestMapper.mapToLabTestDTO(labTestRepository.getById(id_test));
            return new ResponseEntity<>(labTestByID, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity update(long id, LabTestDTO labTestDTO){
        try{
            labTestDTO.setId(id);
            LabTest test = labTestRepository.saveAndFlush(labTestMapper.mapToLabTest(labTestDTO));
            return new ResponseEntity<>(test, HttpStatus.OK);
        }catch (Exception e){
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getAll(){
        try{
            List<LabTestDTO> sortList = labTestMapper.mapToLabTestDTOList(labTestRepository.findAll()) ;
            return new ResponseEntity<>(sortList, HttpStatus.OK);
        }catch (Exception e){
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
