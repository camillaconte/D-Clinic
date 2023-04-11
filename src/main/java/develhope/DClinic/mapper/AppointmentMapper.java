package develhope.DClinic.mapper;

import develhope.DClinic.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AppointmentMapper {


    public Appointment mapToAppointment(final AppointmentDTO appointmentDto) {

    }

    public AppointmentDTO mapToAppointmentDto(final Appointment appointment) {
        return new AppointmentDTO.AppointmentDtoCreated()
                .setId(appointment.getId())
                .setClinic(new Clinic(
                        appointment.getClinic().getId(),
                        appointment.getClinic().getName(),
                        appointment.getClinic().getCity(),
                        appointment.getClinic().getDescription()))

                .setPatient(new Patient(
                        appointment.getPatient().getFirstName(),
                        appointment.getPatient().getLastName()))


                .setDoctor(new Doctor(
                        appointment.getDoctor().getId(),
                        appointment.getDoctor().getFirstName(),
                        appointment.getDoctor().getLastname(),
                        appointment.getDoctor().getSpecialization(),
                        appointment.getDoctor().getReview()))
                .setDate(appointment.getDate())
                .setStatus(appointment.getStatus())
                .setTypology(appointment.getTypology().toString())
                .made();
    }

    public List<AppointmentDTO> mapToAppointmentDtoList(final List<Appointment> appointmentList) {
        return appointmentList.stream()
                .map(a -> new AppointmentDTO.AppointmentDtoCreated()
                        .setId(a.getId())
                        .setClinic(new Clinic(
                                    a.getClinic().getId(),
                                    a.getClinic().getName(),
                                    a.getClinic().getCity(),
                                    a.getClinic().getDescription()))
                            .setPatient(new Patient(
                                    a.getPatient().getFirstName(),
                                    a.getPatient().getLastName()))

                            .setDoctor(new Doctor(
                                    a.getDoctor().getId(),
                                    a.getDoctor().getFirstName(),
                                    a.getDoctor().getLastname(),
                                    a.getDoctor().getSpecialization(),
                                    a.getDoctor().getReview()))
                            .setDate(a.getDate())
                            .setStatus(a.getStatus())
                            .setTypology(a.getTypology().toString())
                            .made())
                            .collect(Collectors.toList());

    }*/

}
