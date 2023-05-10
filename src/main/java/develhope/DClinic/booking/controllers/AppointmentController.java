package develhope.DClinic.controller;

import develhope.DClinic.domain.Appointment;
import develhope.DClinic.domain.BookingDTO;
import develhope.DClinic.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dclinic-appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    Logger log = LoggerFactory.getLogger(AppointmentController.class);


    @PostMapping
    public ResponseEntity createNewAppointment(@RequestBody BookingDTO bookingDTO){
        try {
            appointmentService.bookAppointment(bookingDTO);
            return ResponseEntity.ok().body(bookingDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    @GetMapping("/get-all")
    public ResponseEntity getAllAppointment() {
        return ResponseEntity.ok().body(appointmentService.getAllAppointments());
    }
    @GetMapping("/{patientId}")
    public ResponseEntity getAppointmentByIdPatient(@PathVariable ("patientId") long patientId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body
                    (appointmentService.getAllAppointmentByPatientId(patientId));

        }catch (Exception e){
            log.warn(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Appointment appointment){
        try {
            appointmentService.updateApp(id, appointment);
            return ResponseEntity.ok().body(appointment);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }



    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id){
        try {
            appointmentService.deleteAppById(id);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    }

