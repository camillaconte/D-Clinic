package develhope.DClinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @SequenceGenerator(
            name = "patient_id_sequence",
            sequenceName = "patient_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_id_sequence")
    private long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(name = "fiscalCode")
    private String fiscalCode;

    @Column(nullable = false)
    private String email;

    /**
     * Qui settiamo la REFERENCING SIDE
     * della ASSOCIAZIONE BIDIREZIONALE tra l'entità Paziente e i suoi MedicalReports
     * Valore che attribuisco a  "mappedBy": nome del campo lato "owinign" dove viene stabilita l'associazione.
     * Nel nostro caso è "patient" (perché il MedicalReport ha un field patient patient)
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    @JsonIgnore
    private List<MedicalReport> medicalReportsList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="patient", nullable = false)
    private Set<LabTest> labTest;


    public Patient(){}

    public Patient(long id, String name, String surname, String email, List<MedicalReport> medicalRecordsList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.medicalReportsList = medicalRecordsList;
    }

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
