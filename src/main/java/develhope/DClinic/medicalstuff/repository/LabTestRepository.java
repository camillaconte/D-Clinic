package develhope.DClinic.medicalstuff.repository;

import develhope.DClinic.booking.repositories.D_ClinicRepository;
import develhope.DClinic.medicalstuff.domain.entities.LabTest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Luca Giorgi
 * Repository
 */


@Repository
public interface LabTestRepository extends D_ClinicRepository<LabTest> {
    List<LabTest> findAllByPatientId(long id);
}
