package develhope.DClinic.repository;

import develhope.DClinic.domain.Slot;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlotRepository extends D_ClinicRepository <Slot>{
    Optional<Slot> findAllSlotsByDoctorId(long doctorId);

    Slot findById(long slotId);
    Optional<Slot> findSlotByOccupied(boolean occupied);

}
