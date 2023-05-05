package develhope.DClinic.booking.repositories;

import develhope.DClinic.booking.domain.entities.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AppointmentRepository extends D_ClinicRepository<Appointment> {
}
