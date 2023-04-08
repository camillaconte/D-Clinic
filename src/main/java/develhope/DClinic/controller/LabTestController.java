package develhope.DClinic.controller;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestRequestDTO;
import develhope.DClinic.domain.LabTestResponseDTO;
import develhope.DClinic.service.CheckEmptyField;
import develhope.DClinic.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping
    public ResponseEntity insetTest(@RequestBody LabTestRequestDTO labTestRequestDTO){
        try{
            LabTest newEntity = labTestService.insertNewTest(labTestRequestDTO);
            return ResponseEntity.ok(newEntity);
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        try{
            labTestService.deleteByID(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody LabTestRequestDTO labTest){
        try{
            LabTest update = labTestService.update(id,labTest);
            return ResponseEntity.ok(update);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getLabTestByIdTest(@PathVariable long id){
        try{
            LabTestResponseDTO getByID = labTestService.getByID(id);
            return ResponseEntity.ok(getByID);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllTestOfPatient (@PathVariable long id){
        try{
            List<LabTestResponseDTO> responseDTOList = labTestService.getAllTestOfPatientSV(id);
            return ResponseEntity.ok(responseDTOList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
