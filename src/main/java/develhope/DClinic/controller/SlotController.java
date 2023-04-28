package develhope.DClinic.controller;

import develhope.DClinic.domain.Slot;
import develhope.DClinic.domain.SlotDTO;
import develhope.DClinic.repository.SlotRepository;
import develhope.DClinic.service.SlotService;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/get-all-slots-by-doctorId")
    public ResponseEntity getAllSlotsByDoctorId(@PathVariable long doctorId) {
        try {
            Slot slot = slotService.getAllSlotsByDoctorId(doctorId);
            return ResponseEntity.ok().body(slot);
        } catch (Exception e) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long slotId) {
        try {
            slotService.deleteSlotById(slotId);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
