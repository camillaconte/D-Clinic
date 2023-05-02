package develhope.DClinic.repository;

import develhope.DClinic.domain.Clinic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends D_ClinicRepository<Clinic> {
    List<Clinic> findBycity(String city);

}
