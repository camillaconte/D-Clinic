package develhope.DClinic.user.repositories;

import develhope.DClinic.booking.repositories.D_ClinicRepository;
import develhope.DClinic.user.domain.entities.Patient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends D_ClinicRepository<Patient> {

    Optional<Patient> findPatientByEmail(String email);
    Optional<Patient> findPatientByFiscalCode(String fiscalCode);


}
