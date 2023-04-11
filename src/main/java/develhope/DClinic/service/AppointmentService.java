package develhope.DClinic.service;

import develhope.DClinic.domain.Appointment;
import develhope.DClinic.domain.AppointmentDTo;
import develhope.DClinic.mapper.AppointmentMapper;
import develhope.DClinic.repository.AppointmentRepository;
import develhope.DClinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private CheckEmptyField checkEmptyField;
   /* @Autowired
    public AppointmentService (AppointmentRepository appointmentRepository,DoctorRepository doctorRepository){
        this.appointmentRepository=appointmentRepository;
        this.doctorRepository=doctorRepository;
    }*/

    /*public ResponseEntity createNewAppointment(AppointmentDTo appointmentDTo) {
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
            List<AppointmentDTo> sortList = appointmentMapper.mapToAppointmentDtoList(appointmentRepository.findAll());
            return new ResponseEntity<>(sortList, HttpStatus.OK);
        } catch (Exception e) {
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity updateAppointment(long id, Appointment appointment) {
        try {
            appointment.setId(id);
            appointmentMapper.mapToAppointmentDto(appointmentRepository.saveAndFlush(appointment));
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (Exception e) {
            e.getStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getReferenceByID(long id) {
        try {
            AppointmentDTo appointmentByID = appointmentMapper.mapToAppointmentDto
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










