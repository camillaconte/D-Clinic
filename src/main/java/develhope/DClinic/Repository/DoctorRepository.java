package develhope.DClinic.Repository;

import develhope.DClinic.Domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DoctorRepository extends D_ClinicRepository<Doctor> {
    List<Doctor> findByLastname(String lastname);

}
