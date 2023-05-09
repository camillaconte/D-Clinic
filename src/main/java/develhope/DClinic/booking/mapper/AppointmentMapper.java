package develhope.DClinic.booking.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AppointmentMapper {


   /* public Appointment mapToAppointment(final AppointmentDTo appointmentDto) {
        return new Appointment(
                appointmentDto.getClinicId(),
                new Clinic(
                        appointmentDto.getClinic().getClinicId(),
                        appointmentDto.getClinic().getFirstName(),
                        appointmentDto.getClinic().getCity(),
                        appointmentDto.getClinic().getDescription()),
                new Patient(
                        appointmentDto.getPatient().getFirstName(),
                        appointmentDto.getPatient().getLastName()),
                new Doctor(
                        appointmentDto.getDoctor().getClinicId(),
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
                .setClinicId(appointment.getClinicId())
                .setClinic(new Clinic(
                        appointment.getClinic().getClinicId(),
                        appointment.getClinic().getFirstName(),
                        appointment.getClinic().getCity(),
                        appointment.getClinic().getDescription()))
                .setPatient(new Patient(
                        appointment.getPatient().getFirstName(),
                        appointment.getPatient().getLastName()))
                .setDoctor(new Doctor(
                        appointment.getDoctor().getClinicId(),
                        appointment.getDoctor().getFirstName(),
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
                        .setClinicId(a.getClinicId())
                        .setClinic(new Clinic(
                                a.getClinic().getClinicId(),
                                a.getClinic().getFirstName(),
                                a.getClinic().getCity(),
                                a.getClinic().getDescription()))
                        .setPatient(new Patient(
                                a.getPatient().getFirstName(),
                                a.getPatient().getLastName()))
                        .setDoctor(new Doctor(
                                a.getDoctor().getClinicId(),
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