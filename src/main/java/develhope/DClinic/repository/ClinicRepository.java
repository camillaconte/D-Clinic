package develhope.DClinic.repository;

import develhope.DClinic.domain.Clinic;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicRepository extends D_ClinicRepository<Clinic> {
    Optional<Clinic>  findBycity(String city);

}
