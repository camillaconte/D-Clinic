package develhope.DClinic.controller;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Luca Giorgi
 * Controller per gli esami di laboratorio
 */
@RestController
@RequestMapping("d_clinic/laboratory_test")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;


    @PostMapping
    public ResponseEntity insetTest(@RequestBody LabTest labTest){
        return labTestService.insertNewTest(labTest);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        return labTestService.deleteByID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody LabTest labTest){
        return labTestService.update(id,labTest);
    }

    @GetMapping("/{id}")
    public ResponseEntity getLabTestByIdTest(@PathVariable long id){
        return labTestService.getByID(id);
    }

    @GetMapping
    public ResponseEntity getAndSortAll (){
        return labTestService.getAll();
    }
}
