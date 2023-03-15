package develhope.DClinic.controller;

import develhope.DClinic.entity.LabTestDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author Luca Giorgi
 * Controller per gli esami di laboratorio
 */
@RestController
@RequestMapping("d_clinic/laboratory_test")
public class LabTestController {

    //autowired ---> labTestService

    @PostMapping
    public void create(@RequestBody LabTestDTO labTestDTO){

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id_labtest){

    }

    @PutMapping
    public LabTestDTO update(@RequestBody LabTestDTO labTestDTO) {
        return null;
    }

    @GetMapping("/{id}")
    public LabTestDTO getLabTestByIdPatient(){return null;}


}
