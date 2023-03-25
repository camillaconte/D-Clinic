package develhope.DClinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

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
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

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
    private List<MedicalReport> medicalRecordsList;

    public Patient(){}

    public Patient(int id, String name, String surname, String email, List<MedicalReport> medicalRecordsList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.medicalRecordsList = medicalRecordsList;
    }

    public Patient(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MedicalReport> getMedicalRecordsList() {
        return medicalRecordsList;
    }

    public void setMedicalRecordsList(List<MedicalReport> medicalRecordsList) {
        this.medicalRecordsList = medicalRecordsList;
    }
}
