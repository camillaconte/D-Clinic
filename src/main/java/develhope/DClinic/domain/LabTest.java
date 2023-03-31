package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_test;
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient_id;
    private LocalDateTime date;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tests")
    private Set<LabParameter> labParameter;
    @Column(nullable = false)
    private double value;
    private String description;

    public LabTest() {
    }

    public LabTest(Patient patient, LocalDateTime date, Set<LabParameter> labParameter, double value, String description) {
        this.patient_id = patient;
        this.date = date;
        this.labParameter = labParameter;
        this.value = value;
        this.description = description;
    }

    public long getId_test() {
        return id_test;
    }

    public void setId_test(long id_test) {
        this.id_test = id_test;
    }

    public Patient getPatient() {
        return patient_id;
    }

    public void setPatient(Patient patient) {
        this.patient_id = patient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Set<LabParameter> getLabParameter() {
        return labParameter;
    }

    public void setLabParameter(Set<LabParameter> labParameter) {
        this.labParameter = labParameter;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
