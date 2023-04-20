package develhope.DClinic.domain;

import java.time.LocalDate;
import java.util.Set;

public class LabTestDTOCami {

    private String patientFiscalCode;
    private Set<LabParameter> labParameters;

    public LabTestDTOCami(){}

    public LabTestDTOCami(String patientFiscalCode, Set<LabParameter> labParameters) {
        this.patientFiscalCode = patientFiscalCode;
        this.labParameters = labParameters;
    }

    public String getPatientFiscalCode() {
        return patientFiscalCode;
    }

    public void setPatientFiscalCode(String patientFiscalCode) {
        this.patientFiscalCode = patientFiscalCode;
    }

    public Set<LabParameter> getLabParameters() {
        return labParameters;
    }

    public void setLabParameters(Set<LabParameter> labParameters) {
        this.labParameters = labParameters;
    }
}
