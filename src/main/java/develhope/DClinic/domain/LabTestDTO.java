package develhope.DClinic.domain;

import java.time.LocalDateTime;

public class LabTestDTO {
    private long id;
    private long id_patient_fk;
    private LocalDateTime date;
    private String result;
    private String description;

    public LabTestDTO() {}

    public LabTestDTO(long id, long id_patient_fk, LocalDateTime date, String result, String description) {
        this.id = id;
        this.id_patient_fk = id_patient_fk;
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

    public long getId_patient_fk() {
        return id_patient_fk;
    }

    public void setId_patient_fk(long id_patient_fk) {
        this.id_patient_fk = id_patient_fk;
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
