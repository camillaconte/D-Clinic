package develhope.DClinic.repository;

import develhope.DClinic.domain.Patient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends D_ClinicRepository<Patient> {

    Optional<Patient> findPatientByEmail(String email);
    Patient findPatientByFiscalCode(String fiscalCode);
}
