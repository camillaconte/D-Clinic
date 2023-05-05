package develhope.DClinic.repository;

import develhope.DClinic.domain.Entities.Patient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends D_ClinicRepository<Patient> {

    Optional<Patient> findPatientByEmail(String email);
    Optional<Patient> findPatientByFiscalCode(String fiscalCode);


}
