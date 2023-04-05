package develhope.DClinic.service;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestRequestDTO;
import develhope.DClinic.repository.LabTestRepository;
import develhope.DClinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Luca Giorgi
 * Servizi LabTest
 */
@Service
public class LabTestService   {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private LabTestRepository labTestRepository;
    @Autowired
    private CheckEmptyField checkEmptyField;

    public LabTest insertNewTest(LabTestRequestDTO labTestRequestDTO){
        LabTest test = new LabTest();
        test.setPatient(patientRepository.findPatientByFiscalCode(labTestRequestDTO.getFiscalCode()));
        test.setDate(LocalDate.now());
        test.setNameParameter(labTestRequestDTO.getNameParameter());
        test.setValue(labTestRequestDTO.getValue());
        labTestRepository.save(test);
        return test;
    }

    public void deleteByID(long id_test){
            labTestRepository.deleteById(id_test);
    }

    /*public ResponseEntity getByID (long id_test){
        try {
            LabTestRequestDTO labTestByID = labTestMapper.mapToLabTestDTO(labTestRepository.getById(id_test));
            return new ResponseEntity<>(labTestByID, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity update(long id, LabTestRequestDTO labTestRequestDTO){
        try{
            labTestRequestDTO.setId(id);
            LabTest test = labTestRepository.saveAndFlush(labTestMapper.mapToLabTest(labTestRequestDTO));
            return new ResponseEntity<>(test, HttpStatus.OK);
        }catch (Exception e){
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getAll(){
        try{
            List<LabTestRequestDTO> sortList = labTestMapper.mapToLabTestDTOList(labTestRepository.findAll()) ;
            return new ResponseEntity<>(sortList, HttpStatus.OK);
        }catch (Exception e){
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
