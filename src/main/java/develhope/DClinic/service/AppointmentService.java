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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private PatientRepository patientRepository;
    Logger logger = LoggerFactory.getLogger(AppointmentService.class);


    public Appointment bookAppointment(BookingDTO bookingDTO)throws Exception{
        Optional<Patient>optionalPatient= patientRepository.findById(bookingDTO.getPatientId());
        Optional<Slot>optionalSlot = slotRepository.findById(bookingDTO.getSlotId());
        if(optionalPatient.isEmpty()){
            throw new Exception("The patient with id:" + bookingDTO.getPatientId() +
                    "does not exist");
        }
        if(optionalPatient.isEmpty()){
            throw new Exception("The slot with id:" + bookingDTO.getSlotId() +
                    "does not exist");
        }
        Patient patient = optionalPatient.get();
        Slot slot =optionalSlot.get();

        Appointment appointment =new Appointment(patient, slot);
        appointmentRepository.save(appointment);
        patient.getAppointments().add(appointment);
        patientRepository.save(patient);

        appointment.setClinic(slot.getClinic());
        appointment.setDoctor(slot.getDoctor());
        appointment.setDateAndTime(slot.getDateAndTime());
        appointment.setNotes(bookingDTO.getNotes());
        slot.setOccupied(true);
        //slot.getMedicalService();
        slotRepository.save(slot);
        Appointment app= appointmentRepository.save(appointment);
        logger.info("Appointment" + app.getDateAndTime()  + "created with id:" + app.getId());
        return app;
    }
    public List<Appointment> getAllAppointments() {
        List<Appointment> newListAppointments = appointmentRepository.findAll();
        logger.info("List of all appointments");
        return newListAppointments;

    }
    public List<Appointment>getAllAppointmentByPatientId( long patientId)throws Exception{

        List<Appointment>appointments= patientRepository.findById(patientId).get().getAppointments();
        if (appointments.isEmpty()){
            throw new Exception("appointment not found");
        }
        logger.info("appointment found");
        return appointments;
    }
    public Appointment updateApp(long appointmentId, Appointment appointment) throws Exception {
        appointment.setId(appointmentId);
        if (appointment == null) {
            throw new Exception("appointment not found unable to update");
        }
        appointment.setSlot(appointment.getSlot());
        appointment.setPatient(appointment.getPatient());
        appointment.setNotes(appointment.getNotes());
        appointment.setClinic(appointment.getClinic());
        appointment.setDoctor(appointment.getDoctor());
        appointment.setDateAndTime(appointment.getDateAndTime());


        Appointment newApp = appointmentRepository.saveAndFlush(appointment);
        logger.info("Updated appointment" + newApp.getSlot() + newApp.getPatient() +
                newApp.getDateAndTime() + newApp.getDoctor() + newApp.getNotes() );
        return newApp;
    }
    public void deleteAppById(long appointmentId) throws Exception {
        boolean appExists = appointmentRepository.existsById(appointmentId);
        if (!appExists) {
            throw new Exception("appointment does not exist");
        }

        appointmentRepository.deleteById(appointmentId);
        logger.info("Deleted appointment");
    }



    }










