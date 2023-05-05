package develhope.DClinic.domain.DTO;

import develhope.DClinic.domain.Clinic;
import develhope.DClinic.domain.Entities.Doctor;
import develhope.DClinic.domain.Entities.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class AppointmentDTo {
    private long id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private Clinic clinic;
    private String status;
    private List<String> typology;

    public static class AppointmentDtoCreated {
        private long id;

        private Patient patient;
        private Doctor doctor;
        private LocalDate date;
        private String status;
        private Clinic clinic;
        private List<String> typology = new ArrayList<>();

        public AppointmentDtoCreated() {
        }

        public AppointmentDtoCreated(long id, Patient patient, Doctor doctor, LocalDate date,Clinic clinic,
                                     String status, List<String> typology) {
            this.id = id;
            this.patient = patient;
            this.doctor = doctor;
            this.date = date;
            this.clinic=clinic;
            this.status = status;
            this.typology = typology;
        }

        public long getId() {
            return id;
        }


        public Patient getPatient() {
            return patient;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getStatus() {
            return status;
        }

        public List<String> getTypology() {
            return typology;
        }

        public Clinic getClinic() {
            return clinic;
        }

        public AppointmentDtoCreated setClinic(Clinic clinic) {
            this.clinic = clinic;
            return this;
        }

        public AppointmentDtoCreated setId(long id) {
            this.id = id;
            return this;
        }



        public AppointmentDtoCreated setPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public AppointmentDtoCreated setDoctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public AppointmentDtoCreated setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public AppointmentDtoCreated setStatus(String status) {
            this.status = status;
            return this;
        }

        public AppointmentDtoCreated setTypology(String type) {
            typology.add(type);
            return this;
        }


        public AppointmentDTo made() {
            return new AppointmentDTo(id, patient, doctor, date,clinic, status,typology);
        }

    }

    public AppointmentDTo() {
    }

    public AppointmentDTo(long id, Patient patient, Doctor doctor, LocalDate date, Clinic clinic,
                          String status, List<String> typology) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.clinic=clinic;
        this.status = status;
        this.typology = typology;


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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
