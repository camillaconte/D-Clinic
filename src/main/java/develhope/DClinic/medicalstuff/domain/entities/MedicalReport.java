package develhope.DClinic.medicalstuff.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import develhope.DClinic.user.domain.entities.Doctor;
import develhope.DClinic.user.domain.entities.Patient;
import develhope.DClinic.user.utils.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;


/**
 *  @author Camilla Conte
 *  Entity per i referti medici
 *  */

@Entity
@Table(name = "medical_reports")
public class MedicalReport extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "medicalReports_id_sequence",
            sequenceName = "medicalReports_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medicalReports_id_sequence")
    private long medicalReportId;

    @Column(nullable = false)
    private String reportName;

    /**"siamo i Medical Reports e siamo TANTI per OGNI paziente"
     * ogni report HA UN SOLO PAZIENTE (associato)
     * le annotazioni che seguono definiscono la OWNING SIDE e dicono:
     * l'entità MedicalReport avrà una foreign key column chiamata "patient_id"
     * che farà riferimento al "primary attribute" medicalReportId dell'entità Patient.
     * Ora non resta che settare la REFERENCING SIDE
     */

    //tolto FetchType.LAZY perché dava errore nella creazione del bean
    @ManyToOne (fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToOne (fetch = FetchType.LAZY)
    private Doctor doctor;

    /**
     * TODO prevedere diversi tipi di Medical Report fra i quali il medico
     * può scegliere, al momento di scrivere il referto
     * Perché potrebbe voler scrivere:
     * - un semplice testo libero
     * - modificare testo da un template
     * - ...
     * Potrebbe servirmi una enum (TypeOfMedicalReport typeOfReport)?
     * Ricorda enum are public, static and final (unchangeable - cannot be overridden)
     */

    @Column
    private String history;

    @Column
    private String historyOfPresentIllness;

    @Column
    private String physicalExam;

    @Column
    String conclusions;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime creationDate;

    public MedicalReport(){}

    public MedicalReport(String reportName){
        this.reportName = reportName;
    }
    public MedicalReport(String reportName, Patient patient) {
        this.reportName = reportName;
        this.patient = patient;
    }

    public MedicalReport(String reportName, Patient patient, Doctor doctor, String history, String historyOfPresentIllness,
                         String physicalExam, String conclusions, LocalDateTime creationDate) {
        this.reportName = reportName;
        this.patient = patient;
        this.doctor = doctor;
        this.history = history;
        this.historyOfPresentIllness = historyOfPresentIllness;
        this.physicalExam = physicalExam;
        this.conclusions = conclusions;
        this.creationDate = creationDate;
    }

    public long getMedicalReportId() {
        return medicalReportId;
    }

    public void setMedicalReportId(long medicalReportId) {
        this.medicalReportId = medicalReportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() { return doctor;}

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

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
