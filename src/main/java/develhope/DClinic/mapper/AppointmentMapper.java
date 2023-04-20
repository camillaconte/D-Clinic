package develhope.DClinic.mapper;

import develhope.DClinic.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AppointmentMapper {


   /* public Appointment mapToAppointment(final AppointmentDTo appointmentDto) {
        return new Appointment(
                appointmentDto.getId(),
                new Clinic(
                        appointmentDto.getClinic().getId(),
                        appointmentDto.getClinic().getName(),
                        appointmentDto.getClinic().getCity(),
                        appointmentDto.getClinic().getDescription()),
                new Patient(
                        appointmentDto.getPatient().getFirstName(),
                        appointmentDto.getPatient().getLastName()),
                new Doctor(
                        appointmentDto.getDoctor().getId(),
                        appointmentDto.getDoctor().getFirstname(),
                        appointmentDto.getDoctor().getLastname(),
                        appointmentDto.getDoctor().getSpecialization(),
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
                        appointment.getClinic().getName(),
                        appointment.getClinic().getCity(),
                        appointment.getClinic().getDescription()))
                .setPatient(new Patient(
                        appointment.getPatient().getFirstName(),
                        appointment.getPatient().getLastName()))
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
                                a.getClinic().getName(),
                                a.getClinic().getCity(),
                                a.getClinic().getDescription()))
                        .setPatient(new Patient(
                                a.getPatient().getFirstName(),
                                a.getPatient().getLastName()))
                        .setDoctor(new Doctor(
                                a.getDoctor().getId(),
                                a.getDoctor().getFirstname(),
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