package develhope.DClinic.Domain;

import jakarta.persistence.*;

@Table(name="Clinics")
@Entity
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "office_id")
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "city")
    private String city;

    @Column(name = "description")
    private String description;

    public Clinic(long id, String name, String description) {
    }

    public Clinic(long id, String name,String city, String description) {
        this.id = id;
        this.name = name;
        this.city=city;
        this.description = description;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
