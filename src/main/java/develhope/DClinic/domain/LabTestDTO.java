package develhope.DClinic.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class LabTestDTO implements Serializable {
    private long id;
    private Patient patient;
    private LocalDateTime date;
    private List<LabParameter> labParameter;
    private String description;

    public LabTestDTO() {
    }

    public LabTestDTO(Patient patient, LocalDateTime date, List<LabParameter> labParameter, String description) {
        this.patient = patient;
        this.date = date;
        this.labParameter = labParameter;
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

    public List<LabParameter> getLabParameter() {
        return labParameter;
    }

    public void setLabParameter(List<LabParameter> labParameter) {
        this.labParameter = labParameter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
