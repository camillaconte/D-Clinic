package develhope.DClinic.user.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import develhope.DClinic.booking.domain.entities.Clinic;
import develhope.DClinic.medicalstuff.domain.entities.MedicalReport;
import develhope.DClinic.booking.domain.entities.Slot;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * @author Luca Giorgi
 * Entità del dottore
 */
@Entity
@Table
public class Doctor extends Employee {

    @OneToOne
    private User user;
    private String review;

    @OneToMany(
            mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<MedicalReport> medicalReportsList;

    @OneToMany(
            mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Slot> slots;

    @ManyToOne(fetch = FetchType.LAZY)
    private Clinic clinic;

    @OneToOne
    private User user;

    public Doctor() {
    }

    public Doctor(String review, Set<MedicalReport> medicalReportsList,
                  List<Slot> slots, Clinic clinic) {
        this.review = review;
        this.medicalReportsList = medicalReportsList;
        this.slots = slots;
        this.clinic = clinic;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Set<MedicalReport> getMedicalReportsList() { return medicalReportsList; }

    public void setMedicalReportsList(Set<MedicalReport> medicalReportsList) {
        this.medicalReportsList = medicalReportsList;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
