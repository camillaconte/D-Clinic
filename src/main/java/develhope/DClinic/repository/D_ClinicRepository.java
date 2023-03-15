package develhope.DClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface D_ClinicRepository<T> extends JpaRepository<T, Long> {

    @Override
    <S extends T> S save(S entity);

    @Override
    List<T> findAll();

    @Override
    Optional<T> findById(Long aLong);

    //cerca per codicefiscale

    //??

    @Override
    void deleteById(Long aLong);


}
