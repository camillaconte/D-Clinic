package develhope.DClinic.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class LabTestRequestDTO implements Serializable {
    private String fiscalCode;
    private LocalDate date;
    private double value;
    private String nameParameter;
    private Set<LabParameter> labParameter;
    public LabTestRequestDTO() {
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
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
