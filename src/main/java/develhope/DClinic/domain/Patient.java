package develhope.DClinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "patients")
public class Patient extends Person {

    /**
     * Qui settiamo la REFERENCING SIDE
     * della ASSOCIAZIONE BIDIREZIONALE tra l'entità Paziente e i suoi MedicalReports
     * TODO verificare che sia CascadeType.ALL e non REMOVE
     */
    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<MedicalReport> medicalReportsList;

    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<LabParameter> labParametersList;

    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<LabTest> labTests;

    @OneToOne
    private User user;

    public Patient() {}

    public Patient(Set<MedicalReport> medicalReportsList, List<LabParameter> labParametersList, Set<LabTest> labTests) {
        this.medicalReportsList = medicalReportsList;
        this.labParametersList = labParametersList;
        this.labTests = labTests;
    }


    public Set<MedicalReport> getMedicalReportsList() {
        return medicalReportsList;
    }

    public void setMedicalReportsList(Set<MedicalReport> medicalReportsList) {
        this.medicalReportsList = medicalReportsList;
    }

    public List<LabParameter> getLabParametersList() {
        return labParametersList;
    }

    public void setLabParametersList(List<LabParameter> labParametersList) {
        this.labParametersList = labParametersList;
    }

    public Set<LabTest> getLabTests() {
        return labTests;
    }

    public void setLabTests(Set<LabTest> labTests) {
        this.labTests = labTests;
    }

    public void addLabTest(LabTest labTest) {
        this.labTests.add(labTest);
    }

    public void removeLabTest(int labTestid) {
        this.labTests.remove(labTests.stream().filter(test -> {return test.getTestId() == labTestid;}).findFirst().get());
    }

}
