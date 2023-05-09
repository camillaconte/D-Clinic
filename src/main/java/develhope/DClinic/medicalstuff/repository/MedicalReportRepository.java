package develhope.DClinic.medicalstuff.repository;

import develhope.DClinic.booking.repositories.D_ClinicRepository;
import develhope.DClinic.medicalstuff.domain.entities.MedicalReport;
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

    //@Query("SELECT max(history) FROM clinicdb.medical_reports WHERE patient_id=?1")
    //String findLastHistoryByPatientId(long patientId);*/

    @Query(value = "SELECT clinicdb.medical_reports.history FROM clinicdb.medical_reports WHERE patient_id=?1 ORDER BY creation_date ASC LIMIT 1", nativeQuery = true)
    String findLastHistoryByPatientId(long patientId);









}
