package develhope.DClinic.Mapper;

import develhope.DClinic.Domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AppointmentMapper {
    public Appointment mapToAppointment(final AppointmentDTo.AppointmentDtoCreated appointmentDto) {
        return new Appointment(
                appointmentDto.getId(),
                new Clinic(
                        appointmentDto.getClinic().getId(),
                        appointmentDto.getClinic().getNumber(),
                        appointmentDto.getClinic().getDescription()),
                new Patient(
                        appointmentDto.getPatient().getId(),
                        appointmentDto.getPatient().getName(),
                        appointmentDto.getPatient().getLastname()),

                new Doctor(
                        appointmentDto.getDoctor().getId(),
                        appointmentDto.getDoctor().getName(),
                        appointmentDto.getDoctor().getLastname(),
                        appointmentDto.getDoctor().getSpecialisation(),
                        appointmentDto.getDoctor().getReview()),
                appointmentDto.getDate(),
                appointmentDto.getStatus(),
                appointmentDto.getTypology());
    }

    public AppointmentDTo mapToAppointmentDto(final Appointment appointment) {
        return new AppointmentDTo.AppointmentDtoCreated()
                .setId(appointment.getId())
                .setClinic(new Clinic(
                        appointment.getClinic().getId(),
                        appointment.getClinic().getNumber(),
                        appointment.getClinic().getDescription()))

                .setPatient(new Patient(
                        appointment.getPatient().getId(),
                        appointment.getPatient().getName(),
                        appointment.getPatient().getLastname()))


                .setDoctor(new Doctor(
                        appointment.getDoctor().getId(),
                        appointment.getDoctor().getName(),
                        appointment.getDoctor().getLastname(),
                        appointment.getDoctor().getSpecialisation(),
                        appointment.getDoctor().getReview()))
                .setDate(appointment.getDate())
                .setStatus(appointment.getStatus())
                .setTypology(appointment.getTypology().toString())
                .made();
    }

    public List<AppointmentDTo> mapToAppointmentDtoList(final List<Appointment> appointmentList) {
        return appointmentList.stream()
                .map(a -> new AppointmentDTo.AppointmentDtoCreated()
                        .setId(a.getId())
                        .setClinic(new Clinic(
                                    a.getClinic().getId(),
                                    a.getClinic().getNumber(),
                                    a.getClinic().getDescription()))
                            .setPatient(new Patient(
                                    a.getPatient().getId(),
                                    a.getPatient().getName(),
                                    a.getPatient().getLastname()))

                            .setDoctor(new Doctor(
                                    a.getDoctor().getId(),
                                    a.getDoctor().getName(),
                                    a.getDoctor().getLastname(),
                                    a.getDoctor().getSpecialisation(),
                                    a.getDoctor().getReview()))
                            .setDate(a.getDate())
                            .setStatus(a.getStatus())
                            .setTypology(a.getTypology().toString())
                            .made())
                            .collect(Collectors.toList());

    }

}
