package develhope.DClinic.medicalstuff.controller;

import develhope.DClinic.medicalstuff.domain.entities.LabTest;
import develhope.DClinic.medicalstuff.domain.DTO.LabTestDTOCami;
import develhope.DClinic.medicalstuff.domain.DTO.LabTestRequestDTO;
import develhope.DClinic.medicalstuff.domain.DTO.LabTestResponseDTO;
import develhope.DClinic.utils.CheckEmptyField;
import develhope.DClinic.medicalstuff.service.LabTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;


/**
 * @author Luca Giorgi
 * Controller per gli esami di laboratorio
 */
@RestController
@RequestMapping("d_clinic/laboratory_test")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;
    @Autowired
    private CheckEmptyField checkEmptyField;

    private Logger LOGGER = LoggerFactory.getLogger(LabTestController.class);

    //Lorenzo e Carlo fanno così - verifichiamo insieme cosa meglio usare
    //o se è lo stesso
    private Logger log = LoggerFactory.getLogger(LabTestController.class);


    @PostMapping
    public ResponseEntity insetTest(@RequestBody LabTestRequestDTO labTestRequestDTO){
        HashSet<String> error = checkEmptyField.checkEmptyFieldNewLabTest(labTestRequestDTO);
        if(error.isEmpty()){
            try{
                LabTest newEntity = labTestService.insertNewTest(labTestRequestDTO);
                return ResponseEntity.ok(newEntity);
            }catch (Exception ex) {
                LOGGER.warn(ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * @author Camilla Conte
     * Alternative method to insert a new LabTest
     * including the Set of LabParameter
     *
     */
    @PostMapping("/insert-test-cami")
    public ResponseEntity insertNewLabTestCami(@RequestBody LabTestDTOCami labTestDTOCami){
        try {
            log.info("Trying to create new lab test...");
            labTestService.insertNewLabTestCami(labTestDTOCami);
            log.info("New Lab test created!");
            return ResponseEntity.status(HttpStatus.CREATED).body
                    ("Created new Lab Test for patient with fiscal code " + labTestDTOCami.getPatientFiscalCode());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteBYID(@PathVariable long id){
        try{
            labTestService.deleteByID(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            LOGGER.error("The test does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody LabTestRequestDTO labTest){
        try{
            LabTest update = labTestService.update(id,labTest);
            return ResponseEntity.ok(update);
        }catch (Exception e){
            LOGGER.error("The test to be modified does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity getLabTestByIdTest(@PathVariable long id){
        try{
            LabTestResponseDTO getByID = labTestService.getByID(id);
            return ResponseEntity.ok(getByID);
        }catch (Exception e){
            LOGGER.error("The test does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{fiscalCode}")
    public ResponseEntity getAllTestOfPatient (@PathVariable String fiscalCode){
        try{
            List<LabTestResponseDTO> responseDTOList = labTestService.getAllTestOfPatientSV(fiscalCode);
            return ResponseEntity.ok(responseDTOList);
        }catch (Exception e){
            LOGGER.error("The patient has no test to display");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
