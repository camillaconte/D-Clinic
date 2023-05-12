package develhope.DClinic.user.controllers;

import develhope.DClinic.user.domain.dto.DoctorRequestDTO;
import develhope.DClinic.user.domain.dto.DoctorResponseDTO;
import develhope.DClinic.user.domain.entities.Doctor;
import develhope.DClinic.user.domain.entities.User;
import develhope.DClinic.utils.CheckEmptyField;
import develhope.DClinic.user.service.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;


/**
 * @author Luca Giorgi
 * Controller per Doctor
 */

@RestController
@RequestMapping("d_clinic/doctor")
class DoctorController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    private CheckEmptyField checkEmptyField;

    private Logger LOGGER = LoggerFactory.getLogger(DoctorController.class);

    @PostMapping
    public ResponseEntity insetNewDoctor(@RequestBody DoctorRequestDTO dto/*HttpServletRequest request*/){
        HashSet<String> error = checkEmptyField.checkEmptyFieldNewDoctor(dto);
        if(error.isEmpty()){
            try{
                Doctor doctor = doctorService.insertNewDoctorSV(dto/*request*/);
                return ResponseEntity.ok(doctor);
            }catch (Exception ex) {
                LOGGER.warn("----   ----    ----    ----    ----");
                LOGGER.warn("IT IS NOT POSSIBLE TO CREATE THE DOCTOR");
                LOGGER.warn("----   ----    ----    ----    ----");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @DeleteMapping("/{fiscalCode}")
    public ResponseEntity deleteByFiscalCode(@PathVariable String fiscalCode){
        try{
            doctorService.deleteDoctorByFiscalCodeSV(fiscalCode);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            LOGGER.warn("----   ----    ----    ----    ----");
            LOGGER.warn("IT IS NOT POSSIBLE TO DELETE THE DOCTOR WITH THE FISCAL CODE: " + fiscalCode);
            LOGGER.warn("----   ----    ----    ----    ----");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{fiscalCode}")
    public ResponseEntity update(@PathVariable String fiscalCode, @RequestBody DoctorRequestDTO dto){
        try{
            Doctor update = doctorService.updateDoctorSV(fiscalCode, dto);
            return ResponseEntity.ok(update);
        }catch (Exception e){
            LOGGER.warn("----   ----    ----    ----    ----");
            LOGGER.warn("IT IS NOT POSSIBLE TO MODIFIED THE DOCTOR WITH THE FISCAL CODE: " + fiscalCode);
            LOGGER.warn("----   ----    ----    ----    ----");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{fiscalCode}")
    public ResponseEntity getDoctorByFiscalCode(@PathVariable String fiscalCode){
        try{
            DoctorResponseDTO output = doctorService.getByFiscalCodeSV(fiscalCode);
            return ResponseEntity.ok(output);
        }catch (Exception e){
            LOGGER.warn("----   ----    ----    ----    ----");
            LOGGER.warn("IT IS NOT POSSIBLE TO GET THE DOCTOR WITH THE FISCAL CODE: " + fiscalCode);
            LOGGER.warn("----   ----    ----    ----    ----");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllDoctors(){
        try{
            List<DoctorResponseDTO> responseDTOList = doctorService.getAllDoctorSV();
            return ResponseEntity.ok(responseDTOList);
        }catch (Exception e){
            LOGGER.warn("----   ----    ----    ----    ----");
            LOGGER.warn("IT IS NOT POSSIBLE TO OBTAIN THE LIST OF DOCTORS");
            LOGGER.warn("----   ----    ----    ----    ----");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
