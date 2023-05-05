package develhope.DClinic.controller;

import develhope.DClinic.domain.DTO.ClinicDTO;
import develhope.DClinic.service.CheckEmptyField;
import develhope.DClinic.service.ClinicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/dclinic/clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private CheckEmptyField checkEmptyField;

    Logger log = LoggerFactory.getLogger(ClinicController.class);

    @PostMapping("/create-clinic")
    public ResponseEntity createClinic(@RequestBody ClinicDTO clinicDTO){
        HashSet<String> error = checkEmptyField.checkEmptyFieldNewClinic(clinicDTO);
        try {
            if (error.isEmpty()) {
                clinicService.createClinic(clinicDTO);
                log.info("New clinic inserted!");
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("New clinic with name " + clinicDTO.getName() + " inserted!");
        } catch (Exception e){
            e.getMessage();
            log.error(error.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/{clinicId}")
    public ResponseEntity getClinicById(@PathVariable("clinicId") long clinicId)  {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body( clinicService.getClinic(clinicId));
        }  catch (Exception e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    }
    @GetMapping
    public ResponseEntity getClinicByCity(@RequestParam String city)  {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body( clinicService.getClinicByCity(city));
        }  catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
