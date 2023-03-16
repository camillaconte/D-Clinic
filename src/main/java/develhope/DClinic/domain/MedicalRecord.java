package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "medicalRecords")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    //"siamo i MedicalRecords e siamo TANTI per OGNI paziente"
    @ManyToOne
    @JoinColumn(name = "course_name")
    private Patient patient;

    //enum are public, static and final (unchangeable - cannot be overridden)
    enum Type {
        SHORT,
        EXTENDED
    }

    @Column
    private String history;

    @Column
    private String historyOfPresentIllness;

    @Column
    private String physicalExam;

    @Column
    String conclusions;

    @Column
    private LocalDateTime creationDate;

    public MedicalRecord(){}

    public MedicalRecord(String name, Patient patient) {
        this.name = name;
        this.patient = patient;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getHistoryOfPresentIllness() {
        return historyOfPresentIllness;
    }

    public void setHistoryOfPresentIllness(String historyOfPresentIllness) {
        this.historyOfPresentIllness = historyOfPresentIllness;
    }

    public String getPhysicalExam() {
        return physicalExam;
    }

    public void setPhysicalExam(String physicalExam) {
        this.physicalExam = physicalExam;
    }

    public String getConclusions() {
        return conclusions;
    }

    public void setConclusions(String conclusions) {
        this.conclusions = conclusions;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime now){
        this.creationDate = now;
    }

}
