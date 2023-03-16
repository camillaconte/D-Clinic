package develhope.DClinic.repositories;

import develhope.DClinic.domain.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRecordsRepo extends JpaRepository<MedicalRecord, Integer> {

    Optional<MedicalRecord> findByName(String name);

}
