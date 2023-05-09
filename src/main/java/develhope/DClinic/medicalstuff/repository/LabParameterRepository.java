package develhope.DClinic.medicalstuff.repository;

import develhope.DClinic.booking.repositories.D_ClinicRepository;
import develhope.DClinic.medicalstuff.domain.entities.LabParameter;

import java.util.Set;

public interface LabParameterRepository extends D_ClinicRepository<LabParameter> {

    Set<LabParameter> getByLabTestTestId(long id);
}
