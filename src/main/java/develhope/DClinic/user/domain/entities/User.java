package develhope.DClinic.user.domain.entities;

import develhope.DClinic.security.entity.Role;
import develhope.DClinic.security.token.Token;
import develhope.DClinic.user.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * At the moment (28/04/2023) class created to manage UPLOAD and DOWNLOAD
 * ---> TODO far estendere la SuperClass user alle altre entities
 * In quel caso mappare User come @MappedSuperClass ?????
 *
 * @author camillaconte
 */
@Data
@Builder
@AllArgsConstructor
@Entity
//TODO better undestand the use of "indexes"
@Table(name = "users", indexes = {
        @Index(unique = true, name = "fiscalCode_idx", columnList = "fiscalCode")})
public class User extends BaseEntity implements UserDetails {

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

    private String firstname;
    private String lastname;

    /** N.B. creo un vincolo unico per le mail */
    @Column(unique = true)
    private String fiscalCode;
    private String password;

    @OneToOne(mappedBy = "user")
    private Patient patient;
    @OneToOne(mappedBy = "user")
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getDeclaringClass().getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return fiscalCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
}

