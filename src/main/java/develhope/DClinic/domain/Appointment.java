package develhope.DClinic.domain;

import develhope.DClinic.domain.Entities.Doctor;
import develhope.DClinic.domain.Entities.Patient;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "appointment_date")
    private LocalDate date;

    @Column(name = "status")
    private String status;

   // @Column(name = "typology")
    //@ElementCollection
    //private List<String> typology;

    public Appointment() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
