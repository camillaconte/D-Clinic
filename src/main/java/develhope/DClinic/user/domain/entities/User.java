package develhope.DClinic.user.domain.entities;

import develhope.DClinic.user.domain.entities.Person;
import develhope.DClinic.user.utils.BaseEntity;
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
public class User extends BaseEntity {

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

    /** N.B. creo un vincolo unico per le mail */
    @Column(unique = true)
    private String fiscalCode;
    private String password;

    @OneToOne(mappedBy = "user")
    private Person person;

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

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}

