package develhope.DClinic.booking.services;

import develhope.DClinic.booking.domain.entities.Appointment;
import develhope.DClinic.booking.domain.dto.BookingDTO;
import develhope.DClinic.booking.domain.entities.Slot;
import develhope.DClinic.booking.repositories.AppointmentRepository;
import develhope.DClinic.booking.repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private SlotRepository slotRepository;


    public Appointment bookAppointment(BookingDTO bookingDTO){
        Appointment appointment =new Appointment();
        Slot slot=slotRepository.findById(bookingDTO.getSlotId());
        appointment.setSlot(slotRepository.findById(bookingDTO.getSlotId()));
        appointment.setClinic(slot.getClinic());
        appointment.setDoctor(slot.getDoctor());
        appointment.setPatient(slot.getPatient());
        slot.setOccupied(true);
        slot.getMedicalService();
        slotRepository.save(slot);
        return appointmentRepository.save(appointment);
    }



   /* @Autowired public Appointment bookAppointment(BookingDTO bookingDTO){
        Appointment appointment =new Appointment();
        Slot slot=slotRepository.findById(bookingDTO.getSlotId());
        appointment.setSlot(slotRepository.findById(bookingDTO.getSlotId()));
        appointment.setClinic(slot.getClinic());
        appointment.setDoctor(slot.getDoctor());
        appointment.setPatient(slot.getPatient());
        slot.setOccupied(true);
        slot.getMedicalService();
        slotRepository.save(slot);
        return appointmentRepository.save(appointment);
    }
    public AppointmentService (AppointmentRepository appointmentRepository,DoctorRepository doctorRepository){
        this.appointmentRepository=appointmentRepository;
        this.doctorRepository=doctorRepository;
    }*/

    /*public ResponseEntity createNewAppointment(AppointmentDTO appointmentDTo) {
        HashSet<String> MESSAGE_ERROR = checkEmptyField.checkEmptyFieldNewAppointment(appointmentDTo);
        try {
            if (appointmentDTo.getClinic() != null && appointmentDTo.getDoctor() != null &&
                    appointmentDTo.getPatient() != null) {
                System.out.println("New appointment is insert");
                appointmentRepository.save(appointmentMapper.mapToAppointment(appointmentDTo));
                return new ResponseEntity<>(appointmentDTo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity getAll() {
        try {
            List<AppointmentDTO> sortList = appointmentMapper.mapToAppointmentDtoList(appointmentRepository.findAll());
            return new ResponseEntity<>(sortList, HttpStatus.OK);
        } catch (Exception e) {
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity updateAppointment(long id, Appointment appointment) {
        try {
            appointment.setMedicalReportId(id);
            appointmentMapper.mapToAppointmentDto(appointmentRepository.saveAndFlush(appointment));
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (Exception e) {
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getReferenceByID(long id) {
        try {
            AppointmentDTO appointmentByID = appointmentMapper.mapToAppointmentDto
                    (appointmentRepository.getReferenceById(id));
            return new ResponseEntity<>(appointmentByID, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        public ResponseEntity deleteByID ( long id){
            try {
                appointmentRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }*/
    }










