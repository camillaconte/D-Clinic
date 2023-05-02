package develhope.DClinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.aspectj.weaver.patterns.ConcreteCflowPointcut;

import java.util.List;

/**
 * Entity: Clinic
 * @author camillaconte
 * @author mirkomicocci
 */

@Table(name="Clinics")
@Entity
public class Clinic {

    @Id
    @SequenceGenerator(
            name = "clinic_id_sequence",
            sequenceName = "clinic_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clinic_id_sequence")
    private long clinicId;
    private String name;
    private String city;
    private String description;
    @Enumerated(EnumType.STRING)
    private Room room;

    @OneToMany(
            mappedBy = "clinic",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Slot> slots;

    @OneToMany(
            mappedBy = "clinic",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Doctor> doctors;


    @OneToMany(
            mappedBy = "clinic",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Appointment> appointmentList;

    public Clinic(){}

    public Clinic(String name, String city, String description) {
    }

    public Clinic(long clinicId, String name, String city, String description,
                  Room room, List<Slot> slots, List<Doctor> doctors) {
        this.clinicId = clinicId;
        this.name = name;
        this.city = city;
        this.description = description;
        this.room = room;
        this.slots = slots;
        this.doctors = doctors;
    }

    public Clinic(long clinicId, String name, String city, String description, Room room) {
        this.clinicId = clinicId;
        this.name = name;
        this.city = city;
        this.description = description;
        this.room = room;
    }

    public long getClinicId() {
        return clinicId;
    }

    public void setClinicId(long clinicId) {
        this.clinicId = clinicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room getRoom() { return room; }

    public void setRoom(Room room) { this.room = room; }

    public List<Slot> getSlots() { return slots; }

    public void setSlots(List<Slot> slots) { this.slots = slots; }

    public List<Doctor> getDoctors() { return doctors; }

    public void setDoctors(List<Doctor> doctors) { this.doctors = doctors; }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
