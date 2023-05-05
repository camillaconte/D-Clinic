package develhope.DClinic.repository;

import develhope.DClinic.domain.LabTest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Luca Giorgi
 * Repository
 */


@Repository
public interface LabTestRepository extends D_ClinicRepository<LabTest>{
    List<LabTest> findAllByPatientId(long id);
}
