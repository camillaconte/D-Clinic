package develhope.DClinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<MedicalRecord> medicalRecordsList;

    public Patient(){}

    public Patient(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    public List<MedicalRecord> getMedicalRecordsList() {
        return medicalRecordsList;
    }

    public void setMedicalRecordsList(List<MedicalRecord> medicalRecordsList) {
        this.medicalRecordsList = medicalRecordsList;
    }
}
