package develhope.DClinic.Controller;

import develhope.DClinic.Domain.AppointmentDTo;
import develhope.DClinic.Mapper.AppointmentMapper;
import develhope.DClinic.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dclinic-appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentMapper appointmentMapper;

    @GetMapping
    public List<AppointmentDTo> getAll() {
        return appointmentMapper.mapToAppointmentDtoList(appointmentService.getAll());
    }

    @GetMapping("/{appointmentId}")
    public AppointmentDTo get(@PathVariable long appointmentId)  {    //exception
        return appointmentMapper.mapToAppointmentDto(appointmentService.get(appointmentId)
                .orElseThrow());

    }

    @PostMapping
    public void create(@RequestBody AppointmentDTo.AppointmentDtoCreated appointmentDto) {
        appointmentService.save(appointmentMapper.mapToAppointment(appointmentDto));
    }

    @PutMapping
    public AppointmentDTo update(@RequestBody AppointmentDTo.AppointmentDtoCreated appointmentDto) {
        return appointmentMapper.mapToAppointmentDto(appointmentService.save(
                appointmentMapper.mapToAppointment(appointmentDto)));
    }

    @DeleteMapping("/{appointmentId}")
    public void delete(@PathVariable long appointmentId) {    //exception
        appointmentService.deleteById(appointmentId);
    }


    }

