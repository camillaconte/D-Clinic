package develhope.DClinic.entities;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.RequestMapping;

@Table
@Entity
public class PatientEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String surname;
    private int age;
    @Column(unique = true)
    private String email;
}
