package develhope.DClinic.Repository;

import develhope.DClinic.Repository.MedicalClinicRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AppointmentRepository extends MedicalClinicRepository {
}
