package develhope.DClinic.repository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface LabTestRepository extends D_ClinicRepository{
}
