package develhope.DClinic.domain;

public class ClinicDTO {

    String name;
    String city;
    String description;

    public ClinicDTO(){}

    public ClinicDTO(String name, String city, String description) {
        this.name = name;
        this.city = city;
        this.description = description;
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
