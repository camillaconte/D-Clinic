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

    public Patient(String firstName, String lastName, String fiscalCode, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fiscalCode = fiscalCode;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MedicalReport> getMedicalReportsList() {
        return medicalReportsList;
    }

    public void setMedicalReportsList(List<MedicalReport> medicalReportsList) {
        this.medicalReportsList = medicalReportsList;
    }

    // è sempre un setter, ma non è uno standard, lo devo fare manualmente
    // ne avrò bisogno per aggiungere referti al momento della refertazione
    public void addMedicalReport(MedicalReport medicalReport) {
        this.medicalReportsList.add(medicalReport);
    }

    public Set<LabTest> getLabTest() {
        return labTest;
    }

    public void setLabTest(Set<LabTest> labTest) {
        this.labTest = labTest;
    }
}
