package develhope.DClinic.medicalstuff.domain.DTO;

import java.time.LocalDateTime;

public class MedicalReportDTO {

    String reportName;
    long patientId;
    long doctorId;
    String history;
    String historyOfPresentIllness;
    String physicalExam;
    String conclusions;

    LocalDateTime creationDate;

    public MedicalReportDTO(){}

    public MedicalReportDTO(String reportName, long patientId, long doctorId, String history, String historyOfPresentIllness,
                            String physicalExam, String conclusions) {
        this.reportName = reportName;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.history = history;
        this.historyOfPresentIllness = historyOfPresentIllness;
        this.physicalExam = physicalExam;
        this.conclusions = conclusions;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getDoctorId() { return doctorId;}

    public void setDoctorId(long doctorId) { this.doctorId = doctorId; }

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

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
