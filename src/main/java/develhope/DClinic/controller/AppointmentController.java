package develhope.DClinic.controller;

import develhope.DClinic.domain.BookingDTO;
import develhope.DClinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dclinic-appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;


    @PostMapping
    public ResponseEntity createNewAppointment(@RequestBody BookingDTO bookingDTO){
        try {
            appointmentService.bookAppointment(bookingDTO);
            return ResponseEntity.ok("ok");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /*@PostMapping
    public ResponseEntity create(@RequestBody AppointmentDTO appointmentDto) throws Exception {
        return appointmentService.createNewAppointment(appointmentDto);
    }

    @GetMapping
    public ResponseEntity getAndSortAll() {
        return appointmentService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity getAppointmentById(@PathVariable long id){
        return appointmentService.getReferenceByID(id);


    }

    @PutMapping
    public ResponseEntity update(@PathVariable long id, @RequestBody Appointment appointment){
        return appointmentService.updateAppointment(id,appointment);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        return appointmentService.deleteByID(id);
    }*/


    }

