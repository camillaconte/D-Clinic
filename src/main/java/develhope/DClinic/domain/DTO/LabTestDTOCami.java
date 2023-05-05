package develhope.DClinic.domain.DTO;

import develhope.DClinic.domain.LabParamType;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class LabTestDTOCami {

    private String patientFiscalCode;
    private List<LabParamType> requiredLabParameters;

    public LabTestDTOCami(){}

    public LabTestDTOCami(String patientFiscalCode, List<LabParamType> requiredLabParameters) {
        this.patientFiscalCode = patientFiscalCode;
        this.requiredLabParameters = requiredLabParameters;
    }

    public String getPatientFiscalCode() {
        return patientFiscalCode;
    }

    public void setPatientFiscalCode(String patientFiscalCode) {
        this.patientFiscalCode = patientFiscalCode;
    }

    public List<LabParamType> getRequiredLabParameters() {
        return requiredLabParameters;
    }

    public void setRequiredLabParameters(List<LabParamType> requiredLabParameters) {
        this.requiredLabParameters = requiredLabParameters;
    }
}
