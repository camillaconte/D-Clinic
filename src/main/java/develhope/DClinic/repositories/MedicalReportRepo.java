package develhope.DClinic.repositories;

import develhope.DClinic.domain.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalReportRepo extends JpaRepository<MedicalReport, Integer> {

    Optional<MedicalReport> findByName(String name);

}
