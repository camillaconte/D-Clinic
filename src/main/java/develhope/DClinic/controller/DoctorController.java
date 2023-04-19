package develhope.DClinic.controller;

import develhope.DClinic.domain.*;
import develhope.DClinic.service.CheckEmptyField;
import develhope.DClinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    //Fatta piccola modifica perché altrimenti il nuovo Doctor non veniva salvato nel DB - cami
    //TODO sistemare typo (inset --> insert)
    @PostMapping
    public ResponseEntity insetNewDoctor(@RequestBody DoctorRequestDTO dto){
        HashSet<String> error = checkEmptyField.checkEmptyFieldNewDoctor(dto);
        try{
            if(error.isEmpty()){
                //Doctor newEntity = doctorService.insertNewDoctorSV(dto);
                doctorService.insertNewDoctorSV(dto);
            }
            //return ResponseEntity.ok().build();
            return ResponseEntity.ok().body(dto);

        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
    }


    @DeleteMapping("/{fiscalCode}")
    public ResponseEntity deleteByFiscalCode(@PathVariable String fiscalCode){
        try{
            doctorService.deleteDoctorByFiscalCodeSV(fiscalCode);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{fiscalCode}")
    public ResponseEntity update(@PathVariable String fiscalCode, @RequestBody DoctorRequestDTO dto){
        try{
            Doctor update = doctorService.updateDoctorSV(fiscalCode, dto);
            return ResponseEntity.ok(update);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{fiscalCode}")
    public ResponseEntity getDoctorByFiscalCode(@PathVariable String fiscalCode){
        try{
            DoctorResponseDTO output = doctorService.getByFiscalCodeSV(fiscalCode);
            return ResponseEntity.ok(output);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllDoctors(){
        try{
            List<DoctorResponseDTO> responseDTOList = doctorService.getAllDoctorSV();
            return ResponseEntity.ok(responseDTOList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
