package develhope.DClinic.repository;

import develhope.DClinic.domain.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AppointmentRepository extends D_ClinicRepository<Appointment> {
}
