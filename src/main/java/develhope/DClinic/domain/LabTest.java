package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient_fk", referencedColumnName = "id_patient")
    private Patient patient;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "result")
    private String result;
    @Column(name = "description")
    private String description;

    public LabTest() {}

    public LabTest(long id_test, Patient patient, String result, String description) {
        this.id_test = id_test;
        this.patient = patient;
        this.date = LocalDateTime.now();
        this.result = result;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
