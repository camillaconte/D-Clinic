package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;


/**
 *  @author Camilla Conte
 *  Entity per i referti medici
 *  */

@Entity
@Table(name = "medical_reports")
public class MedicalReport {

    @Id
    @SequenceGenerator(
            name = "medicalReports_id_sequence",
            sequenceName = "medicalReports_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medicalReports_id_sequence")
    private int id;

    @Column(nullable = false)
    private String reportName;

    /**"siamo i Medical Reports e siamo TANTI per OGNI paziente"
     * ogni report HA UN SOLO PAZIENTE (associato)
     * le annotazioni che seguono definiscono la OWNING SIDE e dicono:
     * l'entità MedicalReport avrà una foreign key column chiamata "patient_id"
     * che farà riferimento al "primary attribute" id dell'entità Patient.
     * Ora non resta che settare la REFERENCING SIDE
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    /**
     * Come si potrebbero prevedere diversi tipi di Medical Report fra i quali il medico
     * può scegliere, al momento di scrivere il referto?
     * Perché potrebbe voler scrivere:
     * - un semplice testo libero
     * - modificare testo da un template
     * - ...
     *
     * Potrebbe servirmi una enum?
     * Uhmmm, forse invece sarebbe meglio avere dei MedicalReportDTO con campi diversi?
     *
     * Ricorda enum are public, static and final (unchangeable - cannot be overridden)
     *
     * OPPURE MI SERVE SEMPLICEMENTE IL FIELD TYPE???
     */

    /**
     * N.B. potrebbe essere utile poter richiamare anche solo il field "hystory" dall'ultimo referto
     * del paziente, così da limitarsi ad aggiornarlo alla visita successiva!
     * Il metodo potrebbe essere un semplice getHystory...che poi il front end si prende e appiccica
     * nella finestra di testo che mette a disposizione dell'utente dottore!
     */
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

    public MedicalReport(){}

    public MedicalReport(String reportName){
        this.reportName = reportName;
    }
    public MedicalReport(String reportName, Patient patient) {
        this.reportName = reportName;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
