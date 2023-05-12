package develhope.DClinic.user.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import develhope.DClinic.booking.domain.entities.Appointment;
import develhope.DClinic.medicalstuff.domain.entities.LabParameter;
import develhope.DClinic.medicalstuff.domain.entities.LabTest;
import develhope.DClinic.medicalstuff.domain.entities.MedicalReport;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
@Table
public class Patient extends Person {

    @OneToOne
    private User user;

    /**
     * Qui settiamo la REFERENCING SIDE
     * della ASSOCIAZIONE BIDIREZIONALE tra l'entità Paziente e i suoi MedicalReports
     * TODO verificare che sia CascadeType.ALL e non REMOVE
     */
    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<MedicalReport> medicalReportsList;

    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<LabParameter> labParametersList;

    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<LabTest> labTests;
    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Appointment> appointments;

    public Patient() {}

    public Set<MedicalReport> getMedicalReportsList() {
        return medicalReportsList;
    }

    public void setMedicalReportsList(Set<MedicalReport> medicalReportsList) {
        this.medicalReportsList = medicalReportsList;
    }

    public List<LabParameter> getLabParametersList() {
        return labParametersList;
    }

    public void setLabParametersList(List<LabParameter> labParametersList) {
        this.labParametersList = labParametersList;
    }

    public Set<LabTest> getLabTests() {
        return labTests;
    }

    public void setLabTests(Set<LabTest> labTests) {
        this.labTests = labTests;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
