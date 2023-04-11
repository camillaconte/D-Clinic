package develhope.DClinic.domain;

import java.time.LocalDate;
import java.util.List;

public class AppointmentDTO {
    private long id;
    private String fiscalCodePatient;
    private String fiscalCodeDoctor;
    private LocalDate date;
    private long clinicID;
    private String status;
    private List<String> typology;


    public AppointmentDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFiscalCodePatient() {
        return fiscalCodePatient;
    }

    public void setFiscalCodePatient(String fiscalCodePatient) {
        this.fiscalCodePatient = fiscalCodePatient;
    }

    public String getFiscalCodeDoctor() {
        return fiscalCodeDoctor;
    }

    public void setFiscalCodeDoctor(String fiscalCodeDoctor) {
        this.fiscalCodeDoctor = fiscalCodeDoctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getClinicID() {
        return clinicID;
    }

    public void setClinicID(long clinicID) {
        this.clinicID = clinicID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getTypology() {
        return typology;
    }

    public void setTypology(List<String> typology) {
        this.typology = typology;
    }
}
