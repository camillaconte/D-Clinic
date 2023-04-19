package develhope.DClinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Luca Giorgi
 * Entità del dottore
 */
@Entity
@Table
public class Doctor extends Employee {

    private String review;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
    @JsonIgnore
    private Set<MedicalReport> medicalReportsList;

    public Doctor() {
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Set<MedicalReport> getMedicalReportsList() { return medicalReportsList; }

    public void setMedicalReportsList(Set<MedicalReport> medicalReportsList) {
        this.medicalReportsList = medicalReportsList;
    }
}
