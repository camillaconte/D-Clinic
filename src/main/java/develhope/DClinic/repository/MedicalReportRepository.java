package develhope.DClinic.repository;

import develhope.DClinic.domain.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalReportRepository extends D_ClinicRepository<MedicalReport> {

    //Optional<MedicalReport> findByName(String reportName);

    @Query(value = "SELECT * FROM dClinic.medical_reports WHERE id=(SELECT max(id) FROM dClinic.medical_reports);", nativeQuery = true)
    Optional<MedicalReport> findLast();

}
