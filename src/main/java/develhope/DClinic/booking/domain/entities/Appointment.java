package develhope.DClinic.booking.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import develhope.DClinic.booking.domain.entities.Clinic;
import develhope.DClinic.booking.domain.entities.Slot;
import develhope.DClinic.user.domain.entities.Doctor;
import develhope.DClinic.user.domain.entities.Patient;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @SequenceGenerator(   name = "appointment_id_sequence",
            sequenceName = "appointment_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "appointment_id_sequence")
    @Column(name = "appointment_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "clinic_id") <--- capire se va messo
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.LAZY)
    //@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY) <--- Carlo consiglia CascadeType.ALL
    //@JoinColumn(name = "patient_id") <--- capire se va messo
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "doctor_id") <--- capire se va messo
    private Doctor doctor;

    @OneToOne
    private Slot slot;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateAndTime;

    @Column(name = "notes")
    private String notes;

   // @Column(name = "typology")
    //@ElementCollection
    //private List<String> typology;

    public Appointment() {
    }


    public Appointment(Patient patient,Slot slot) {
        this.patient = patient;
        this.slot=slot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
