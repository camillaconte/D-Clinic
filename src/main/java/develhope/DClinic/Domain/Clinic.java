package develhope.DClinic.Domain;

import jakarta.persistence.*;

@Table(name="Clinics")
@Entity
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "office_id")
    private long id;

    @Column(name = "number")
    private int number;

    @Column(name = "description")
    private String description;

    public Clinic() {
    }

    public Clinic(long id, int number, String description) {
        this.id = id;
        this.number = number;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
