package develhope.DClinic.repository;

import develhope.DClinic.domain.Doctor;
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
