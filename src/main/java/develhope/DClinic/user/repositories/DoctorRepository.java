package develhope.DClinic.user.repositories;

import develhope.DClinic.booking.repositories.D_ClinicRepository;
import develhope.DClinic.user.domain.entities.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author Luca Giorgi
 * Repository per Doctor
 */
@Repository
public interface DoctorRepository extends D_ClinicRepository<Doctor> {
    List<Doctor> findByLastname(String lastname);

    Doctor deleteByFiscalCode(String fiscalCode);

    Doctor getByFiscalCode(String fiscalCode);

}
