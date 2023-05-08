package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.util.List;

/**
 * At the moment (28/04/2023) class created to manage UPLOAD and DOWNLOAD
 * ---> TODO far estendere la SuperClass user alle altre entities
 * In quel caso mappare User come @MappedSuperClass ?????
 *
 * @author camillaconte
 */

@Entity
//TODO better undestand the use of "indexes"
@Table(name = "users", indexes = {
        @Index(unique = true, name = "email_idx", columnList = "email")})
public class User {

    @Id
    @SequenceGenerator(
            name = "users_id_sequence",
            sequenceName = "users_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_id_sequence")
    private long id;
    private String firstName;
    private String lastName;

    /** N.B. creo un vincolo unico per le mail */
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    /**
     * All users should have the opportunity to upload
     * a profile picture,
     * especially Doctors, since people like to see the face of the person who will visit them!
     */
    private String profilePictureFileName;

    /**
     * The User Patient should have the opportunity
     * to upload previous documents
     * (e.g. medicalReports and labTests effectuated in other clinics)
     */
    private List<String> documents;

    @OneToOne
    private Patient patient;

    @OneToOne
    private Doctor doctor;

    /*

    posso essere sia paziente che dottore oppure solo uno dei due

    id      username    password     id_paziente        id_dottore
    1       carlo       rnefwerfk    12
    2       mirko       nakn                            65
    3       luca        jfnakjf      34                 45

     */

    public User() {
    }

    public User(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String firstName, String lastName, String email, String profilePictureFileName, List<String> documents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePictureFileName = profilePictureFileName;
        this.documents = documents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureFileName() {
        return profilePictureFileName;
    }

    public void setProfilePictureFileName(String profilePictureFileName) {
        this.profilePictureFileName = profilePictureFileName;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

