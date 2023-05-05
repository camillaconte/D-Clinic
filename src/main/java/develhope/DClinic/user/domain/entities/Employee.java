package develhope.DClinic.user.domain.entities;

import jakarta.persistence.*;

import java.util.UUID;

/**
 * @author Luca Giorgi
 * Classe comune per i lavoratori della clinica
 */
@MappedSuperclass
public class Employee extends Person {

    private String specialization;
    private String Badge;

    public Employee() {
        this.Badge = UUID.randomUUID().toString();
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getBadge() {
        return Badge;
    }

    public void setBadge(String badge) {
        Badge = badge;
    }
}
