package develhope.DClinic.controller;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestDTO;
import develhope.DClinic.service.LabTestService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Luca Giorgi
 * Controller per gli esami di laboratorio
 */
@RestController
@RequestMapping("d_clinic/laboratory_test")
public class LabTestController {

    private LabTestService labTestService;

    @PostMapping
    public void create(@RequestBody LabTestDTO labTestDTO){
        labTestService.save(labTestDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id_labTest){
        labTestService.deleteByID(id_labTest);
    }

    @PutMapping
    public LabTestDTO update(@RequestBody LabTestDTO labTestDTO) {
        return labTestService.save(labTestDTO);
    }

    @GetMapping("/{id}")
    public Optional<LabTestDTO> getLabTestByIdTest(long id_test){
        return labTestService.getByID(id_test);
    }


}
