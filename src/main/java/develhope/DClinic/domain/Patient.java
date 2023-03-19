package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.util.List;


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

    @Column(name = "fiscalCode_patient")
    private List<LabTest> labTest;


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

    public List<LabTest> getLabTest() {
        return labTest;
    }

    public void setLabTest(List<LabTest> labTest) {
        this.labTest = labTest;
    }
}
