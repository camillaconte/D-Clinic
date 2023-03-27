package develhope.DClinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface D_ClinicRepository<T> extends JpaRepository<T, Long> {
    @Override
    List<T> findAll();

    @Override
    Optional<T> findById(Long id);

    @Override
    <S extends T> S save(S entity);

    @Override
    void deleteById(Long id);
}
