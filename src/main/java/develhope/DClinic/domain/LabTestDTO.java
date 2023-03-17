package develhope.DClinic.domain;

import java.time.LocalDateTime;

public class LabTestDTO {
    private long id;
    private Patient patient;
    private LocalDateTime date;
    private String result;
    private String description;

    public LabTestDTO() {}

    public LabTestDTO(long id, Patient patient, LocalDateTime date, String result, String description) {
        this.id = id;
        this.patient = patient;
        this.date = date;
        this.result = result;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
