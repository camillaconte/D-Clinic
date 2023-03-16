package develhope.DClinic.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
@Transactional
public interface LabTestRepository extends D_ClinicRepository{
}
