package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_patient")
    private long id_patient;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "fiscalCode")
    private String fiscalCode;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="patient", nullable = false)
    private Set<LabTest> labTest;


    public Patient(){}

    public long getId() {
        return id_patient;
    }

    public void setId(long id) {
        this.id_patient = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId_patient() {
        return id_patient;
    }

    public void setId_patient(long id_patient) {
        this.id_patient = id_patient;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public Set<LabTest> getLabTest() {
        return labTest;
    }

    public void setLabTest(Set<LabTest> labTest) {
        this.labTest = labTest;
    }
}
