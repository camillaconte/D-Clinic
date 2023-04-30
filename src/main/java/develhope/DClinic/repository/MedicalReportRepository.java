package develhope.DClinic.repository;

import develhope.DClinic.domain.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MedicalReportRepository extends D_ClinicRepository<MedicalReport> {

    Optional<Set<MedicalReport>> findAllByDoctorId(long doctorId);
    Optional<Set<MedicalReport>> findAllByDoctorFiscalCode(String doctorFiscalCode);

    Optional<Set<MedicalReport>> findAllByPatientId(long patientId);
    Optional<Set<MedicalReport>> findAllByPatientFiscalCode(String patientFiscalCode);

    /*@Query("SELECT max(history) FROM clinicdb.medical_reports WHERE patient_id=?1")
    String findLastHistoryByPatientId(long patientId);*/

    //SELECT * FROM clinicdb.medical_reports
    //WHERE patient_id  = 1
    //ORDER BY creation_date ASC
    //LIMIT 1;









}
