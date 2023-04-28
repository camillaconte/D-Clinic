package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name="slots")
public class Slot {
    @Id
    @SequenceGenerator(
            name = "slot_is_sequence",
            sequenceName = "slot_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "slot_id_sequence")
    private long id;
    private LocalDateTime dateAndTime;
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    private Clinic clinic;

    private String medicalService; //fare entity
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @OneToOne
    private Appointment appointment;
    private Boolean occupied;

    public Slot() {
    }

    public Slot(LocalDateTime dateAndTime, Doctor doctor, Clinic clinic) {
        this.dateAndTime = dateAndTime;
        this.doctor = doctor;
        this.clinic = clinic;
    }

    public Slot(long id, LocalDateTime dateAndTime,
                Doctor doctor, Clinic clinic, String medicalService,
                Patient patient, Appointment appointment, Boolean occupied) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.doctor = doctor;
        this.clinic = clinic;
        this.medicalService = medicalService;
        this.patient = patient;
        this.appointment = appointment;
        this.occupied = occupied;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getMedicalService() {
        return medicalService;
    }

    public void setMedicalService(String medicalService) {
        this.medicalService = medicalService;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }
}