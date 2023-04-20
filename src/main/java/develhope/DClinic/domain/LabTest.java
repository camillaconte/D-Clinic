package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long testId;
    @ManyToOne
    private Patient patient;
    private LocalDate date;

    /**
     * TODO mettere al plurale - cami
     */
    @OneToMany(mappedBy = "labTest")
    private Set<LabParameter> labParameter;


    public LabTest() {
    }

    /**
     * @author Camilla Conte
     * Costruttore con i parametri che mi servono per creare un nuovo LabTest
     * nel metodo del service "insertNewLabTestCami"
     */

    public LabTest(Patient patient, Set<LabParameter> labParameters) {
        this.patient = patient;
        this.labParameter = new HashSet<>();
    }



    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<LabParameter> getLabParameter() {
        return labParameter;
    }

    public void setLabParameter(Set<LabParameter> labParameter) {
        this.labParameter = labParameter;
    }

}
