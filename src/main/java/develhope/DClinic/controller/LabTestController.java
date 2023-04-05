package develhope.DClinic.controller;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestRequestDTO;
import develhope.DClinic.service.CheckEmptyField;
import develhope.DClinic.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

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


    /*@DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        return labTestService.deleteByID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody LabTestRequestDTO labTest){
        return labTestService.update(id,labTest);
    }

    @GetMapping("/{id}")
    public ResponseEntity getLabTestByIdTest(@PathVariable long id){
        return labTestService.getByID(id);
    }

    @GetMapping
    public ResponseEntity getAndSortAll (){
        return labTestService.getAll();
    }*/
}
