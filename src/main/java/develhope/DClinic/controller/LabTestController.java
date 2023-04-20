package develhope.DClinic.controller;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestDTOCami;
import develhope.DClinic.domain.LabTestRequestDTO;
import develhope.DClinic.domain.LabTestResponseDTO;
import develhope.DClinic.service.CheckEmptyField;
import develhope.DClinic.service.LabTestService;
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

    public static Logger LOGGER = LoggerFactory.getLogger(LabTestController.class);

    //Lorenzo e Carlo fanno così - verifichiamo insieme cosa meglio usare
    //o se è lo stesso
    Logger log = LoggerFactory.getLogger(MedicalReportsController.class);


    @PostMapping
    public ResponseEntity insetTest(@RequestBody LabTestRequestDTO labTestRequestDTO){
        HashSet<String> error = checkEmptyField.checkEmptyFieldNewLabTest(labTestRequestDTO);
        try{
            if(error.isEmpty()){
                LabTest newEntity = labTestService.insertNewTest(labTestRequestDTO);
            }
            return ResponseEntity.ok().build();
        }catch (Exception ex) {
            LOGGER.error(error.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
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
