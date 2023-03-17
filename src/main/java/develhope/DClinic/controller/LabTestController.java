package develhope.DClinic.controller;

import develhope.DClinic.domain.LabTestDTO;
import develhope.DClinic.service.LabTestService;
import develhope.DClinic.service.ValidationExamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;

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
    private ValidationExamsService validationExamsService;


    /**
     * Dovrebbe essere giusto ma chiedere conferma
     */

    @PostMapping(value = "/insertTest")
    public ResponseEntity insetTest(@RequestBody LabTestDTO labTestDTO){
        HashSet<String> messageErrors = validationExamsService.checkErrorLabTest(labTestDTO);
        try{
            if(labTestDTO.getPatient() != null && labTestDTO.getResult() != null && labTestDTO.getDescription() != null){
                System.out.println("New laboratory test is insert");
                labTestService.save(labTestDTO);
                return new ResponseEntity<>(labTestDTO, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(messageErrors, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(messageErrors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id_labTest){
        try {
            labTestService.deleteByID(id_labTest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody LabTestDTO labTestDTO){
        try {
            labTestService.save(labTestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getLabTestByIdTest(long id_test){
        try {
            Optional<LabTestDTO> labTestByID = labTestService.getByID(id_test);
            return new ResponseEntity<>(labTestByID, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
