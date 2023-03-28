package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Luca Giorgi
 * Entity per gli esami di aboratorio
 */

@Entity
@Table(name = "tb_laboratory_test")
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_test")
    private long id_test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tb_laboratory_test", nullable = false)
    private Patient patient;
    private LocalDateTime date;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tests")
    private Set<LabParameter> labParameter;

    @Column(nullable = false)
    private double value;
    private String description;

    public LabTest() {
    }

    public LabTest(Patient patient, LocalDateTime date, Set<LabParameter> labParameter, String description) {
        this.patient = patient;
        this.date = date;
        this.labParameter = labParameter;
        this.description = description;
    }

    public long getId_test() {
        return id_test;
    }

    public void setId_test(long id_test) {
        this.id_test = id_test;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
