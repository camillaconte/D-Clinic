package develhope.DClinic.controller;

import develhope.DClinic.domain.Slot;
import develhope.DClinic.domain.DTO.SlotDTO;
import develhope.DClinic.repository.SlotRepository;
import develhope.DClinic.service.SlotService;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slot")
public class SlotController {


    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private SlotService slotService;

    Logger log = LoggerFactory.getLogger(SlotController.class);

    @PostMapping("/create")
    public ResponseEntity createSlot(@RequestBody SlotDTO slotDTO) {
        try {
            slotService.createSlot(slotDTO);

            return ResponseEntity.ok().body(slotDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public List<Slot> getAllSlots() {
        return slotService.getAllSlots();
    }



    @GetMapping("/get-slot-by-clinicid")
    public ResponseEntity getSlotsByClinicId(@RequestParam long clinicId)  {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(slotService.getAllSlotsByClinicId(clinicId));
        }catch (Exception e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-slots-by-doctorId/{doctorId}")
    public ResponseEntity getAllSlotsByDoctorId(@PathVariable("doctorId") long doctorId) {
        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(slotService.getAllSlotsByDoctorId(doctorId));
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Slot slot) {
        try {
            slotService.updateSlot(id, slot);
            return ResponseEntity.ok().body(slot);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity delete(@PathVariable ("Id") long Id) {
        try {
            slotService.deleteSlotById(Id);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
