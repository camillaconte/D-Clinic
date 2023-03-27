package develhope.DClinic.domain;

import jakarta.persistence.*;

@Entity
@Table(name="Patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    public Patient() {
    }

    public Patient(long id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
