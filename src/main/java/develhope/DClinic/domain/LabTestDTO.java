package develhope.DClinic.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class LabTestDTO implements Serializable {
    private long id;
    private Patient patient;
    private LocalDate date;
    private Set<LabParameter> labParameter;
    private double value;
    private String description;

    public LabTestDTO() {
    }

    public LabTestDTO(Patient patient, LocalDate date, Set<LabParameter> labParameter, double value, String description) {
        this.patient = patient;
        this.date = date;
        this.labParameter = labParameter;
        this.value = value;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate now) {
        this.date = date;
    }

    public Set<LabParameter> getLabParameter() {
        return labParameter;
    }

    public void setLabParameter(Set<LabParameter> labParameter) {
        this.labParameter = labParameter;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
