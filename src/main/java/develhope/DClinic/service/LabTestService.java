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

    public ResponseEntity insertNewTest(LabTest labTest){
        HashSet<String> MESSAGE_ERROR = checkEmptyField.checkEmptyFieldNewLabTest(labTest);
        try{
            System.out.println("New laboratory test is insert");
            labTestRepository.save(labTest);
            return new ResponseEntity<>(labTest, HttpStatus.OK);
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
            LabTest labTestByID = labTestRepository.getById(id_test);
            return new ResponseEntity<>(labTestByID, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity update(long id, LabTest labTest){
        try{
            labTest.setId_test(id);
            labTestRepository.saveAndFlush(labTest);
            return new ResponseEntity<>(labTest, HttpStatus.OK);
        }catch (Exception e){
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getAll(){
        try{
            List<LabTest> sortList = labTestRepository.findAll();
            return new ResponseEntity<>(sortList, HttpStatus.OK);
        }catch (Exception e){
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
