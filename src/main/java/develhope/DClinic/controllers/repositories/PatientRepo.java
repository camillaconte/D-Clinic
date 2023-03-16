package develhope.DClinic.controllers.repositories;

import develhope.DClinic.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
}
