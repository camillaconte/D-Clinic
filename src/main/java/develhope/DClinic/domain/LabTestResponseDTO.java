package develhope.DClinic.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class LabTestResponseDTO implements Serializable {

    private String uuid;
    private Patient patient;
    private LocalDate date;
    private String nameParameter;
    private double value;

    private Set<LabParameter> labParameter;
    public LabTestResponseDTO() {
    }

    public Patient getPatient() {
        return patient;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getNameParameter() {
        return nameParameter;
    }

    public void setNameParameter(String nameParameter) {
        this.nameParameter = nameParameter;
    }

    public Set<LabParameter> getLabParameter() {
        return labParameter;
    }

    public void setLabParameter(Set<LabParameter> labParameter) {
        this.labParameter = labParameter;
    }
}

