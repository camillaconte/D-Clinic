package develhope.DClinic.service;

import develhope.DClinic.domain.Appointment;
import develhope.DClinic.domain.BookingDTO;
import develhope.DClinic.domain.Patient;
import develhope.DClinic.domain.Slot;
import develhope.DClinic.mapper.AppointmentMapper;
import develhope.DClinic.repository.AppointmentRepository;
import develhope.DClinic.repository.DoctorRepository;
import develhope.DClinic.repository.PatientRepository;
import develhope.DClinic.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private PatientRepository patientRepository;


    public Appointment bookAppointment(BookingDTO bookingDTO)throws Exception{
        Optional<Patient>optionalPatient= patientRepository.findById(bookingDTO.getPatientId());
        if(optionalPatient.isEmpty()){
            throw new Exception("The patient with id:" + bookingDTO.getPatientId() +
                    "does not exist");
        }
        Patient patient = optionalPatient.get();
        Slot slot=slotRepository.findById(bookingDTO.getSlotId());
        Appointment appointment =new Appointment(patient, slot);
        appointmentRepository.save(appointment);
        patient.getAppointments().add(appointment);
        patientRepository.save(patient);

        appointment.setSlot(slotRepository.findById(bookingDTO.getSlotId()));
        appointment.setClinic(slot.getClinic());
        appointment.setDoctor(slot.getDoctor());
        //appointment.setPatient(bookingDTO.getPatientId());
        appointment.setDateAndTime(slot.getDateAndTime());
        appointment.setNotes(bookingDTO.getNotes());
        slot.setOccupied(true);
        //slot.getMedicalService();
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










