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
            if(labTestDTO.getPatient() != null && labTestDTO.getResult() != null && labTestDTO.getDescription() != null){
                System.out.println("New laboratory test is insert");
                labTestRepository.save(labTestMapper.mapToLabTest(labTestDTO));
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
            LabTestDTO labTestByID = labTestMapper.mapToLabTestDTO(labTestRepository.getById(id_test));
            return new ResponseEntity<>(labTestByID, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity update(long id, LabTest labTest){
        try{
            labTest.setId_test(id);
            labTestMapper.mapToLabTestDTO(labTestRepository.saveAndFlush(labTest));
            return new ResponseEntity<>(labTest, HttpStatus.OK);
        }catch (Exception e){
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getAll(){
        try{
            List<LabTestDTO> sortList = labTestMapper.mapToLabTestDTOList(labTestRepository.findAll());
            return new ResponseEntity<>(sortList, HttpStatus.OK);
        }catch (Exception e){
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
