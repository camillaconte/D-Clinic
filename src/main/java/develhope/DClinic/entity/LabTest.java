package develhope.DClinic.entity;

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
    @Column(name = "id_labtest")
    private long id_labtest;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient_fk", referencedColumnName = "id_utente")
    private Patient patient;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "result")
    private String result;
    @Column(name = "description")
    private String description;

    public LabTest() {}

    public LabTest(long id_labtest, Patient patient, String result, String description) {
        this.id_labtest = id_labtest;
        this.patient = patient;
        this.date = LocalDateTime.now();
        this.result = result;
        this.description = description;
    }

    public long getId_labtest() {
        return id_labtest;
    }

    public void setId_labtest(long id_labtest) {
        this.id_labtest = id_labtest;
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
