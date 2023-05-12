package develhope.DClinic.booking.repositories;

import develhope.DClinic.booking.domain.entities.Slot;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends D_ClinicRepository<Slot> {
    List<Slot> findAllSlotsByDoctorId(long doctorId);

    Optional<Slot> findById(long slotId);
    Optional<Slot> findSlotByOccupied(boolean occupied);

}
