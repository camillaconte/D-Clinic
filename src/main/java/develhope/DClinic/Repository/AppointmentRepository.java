package develhope.DClinic.Repository;

import develhope.DClinic.Domain.Appointment;
import develhope.DClinic.Domain.AppointmentDTo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AppointmentRepository extends D_ClinicRepository<Appointment> {
}
