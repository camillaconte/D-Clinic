package develhope.DClinic.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDTo {
    private long id;
    private Patient patient;
    private String fiscalCodeDoctor;
    private LocalDate date;
    private Clinic clinic;
    private String status;
    private List<String> typology;


    public AppointmentDTo() {
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

    public String getFiscalCodeDoctor() {
        return fiscalCodeDoctor;
    }

    public void setFiscalCodeDoctor(String fiscalCodeDoctor) {
        this.fiscalCodeDoctor = fiscalCodeDoctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
