package develhope.DClinic.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class LabTestResponseDTO implements Serializable {

    private long id;
    private Patient patient;
    private LocalDate date;
    private Set<LabParameter> labParameter;
    public LabTestResponseDTO() {
    }

    public Patient getPatient() {
        return patient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<LabParameter> getLabParameter() {
        return labParameter;
    }

    public void setLabParameter(Set<LabParameter> labParameter) {
        this.labParameter = labParameter;
    }
}

