package develhope.DClinic.repository;

import develhope.DClinic.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepositories extends JpaRepository<Patient, Long> {
    @Override
    List<Patient> findAll();

    @Override
    Patient getById(Long aLong);
}
