package develhope.DClinic.repository;

import develhope.DClinic.domain.LabParameter;

import java.util.Set;

public interface LabParameterRepository extends D_ClinicRepository<LabParameter> {

    Set<LabParameter> getByLabTestTestId(long id);
}
