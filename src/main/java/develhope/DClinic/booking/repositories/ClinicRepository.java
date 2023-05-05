package develhope.DClinic.booking.repositories;

import develhope.DClinic.booking.domain.entities.Clinic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends D_ClinicRepository<Clinic> {
    List<Clinic> findBycity(String city);

}
