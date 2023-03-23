package develhope.DClinic.repository;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Luca Giorgi
 * Repository
 */


@Repository
public interface LabTestRepository extends D_ClinicRepository<LabTest>{

}
