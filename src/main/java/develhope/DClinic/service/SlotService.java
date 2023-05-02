package develhope.DClinic.service;

import develhope.DClinic.domain.Clinic;
import develhope.DClinic.domain.Doctor;
import develhope.DClinic.domain.Slot;
import develhope.DClinic.domain.SlotDTO;
import develhope.DClinic.repository.ClinicRepository;
import develhope.DClinic.repository.DoctorRepository;
import develhope.DClinic.repository.SlotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class SlotService {
    @Autowired
     SlotRepository slotRepository;
    @Autowired
     DoctorRepository doctorRepository;
    @Autowired
     ClinicRepository clinicRepository;

    Logger logger = LoggerFactory.getLogger(SlotService.class);



    public Slot createSlot(SlotDTO slotDTO) throws Exception {

        Optional<Clinic> optionalClinic=clinicRepository.findById(slotDTO.getClinicId());
        Optional<Doctor> optionalDoctor = doctorRepository.findById(slotDTO.getDoctorId());
        if (optionalClinic.isEmpty()){
            throw new Exception("The clinic with id:" + slotDTO.getClinicId() + "does not exist");
        }
        if (optionalDoctor.isEmpty()){
            throw new Exception("The doctor with id:" + slotDTO.getDoctorId() + "does not exist");
        }
        Clinic clinic= optionalClinic.get();
        Doctor doctor=optionalDoctor.get();
        Slot newSlot = new Slot(slotDTO.getDateAndTime(), doctor,
                clinic);
        slotRepository.save(newSlot);
        clinic.getSlots().add(newSlot);
        clinicRepository.save(clinic);
        doctor.getSlots().add(newSlot);
        doctorRepository.save(doctor);
        logger.info("Slot" +newSlot.getDateAndTime()  + "created with id:" + newSlot.getId());
        return slotRepository.save(newSlot);
    }

    public List<Slot> getAllSlots() {
        List<Slot> newList = slotRepository.findAll();
        logger.info("List of all slots");
        return newList;

    }
    public List<Slot>getAllSlotsByClinicId( long clinicId)throws Exception{

        List<Slot>slot= clinicRepository.findById(clinicId).get().getSlots();
        if (slot.isEmpty()){
            throw new Exception("slot not found");
        }
        logger.info("Slot found");
        return slot;

    }


    public List<Slot> getAllSlotsByDoctorId(long doctorId) throws Exception {
        List<Slot> optionalSlot = slotRepository.findAllSlotsByDoctorId(doctorId);
        if (optionalSlot.isEmpty()) {
            throw new Exception("slot not found");
        }
        logger.info("Slot found");
        return optionalSlot;
    }

    public Slot updateSlot(long slotId, Slot slot) throws Exception {
        slot.setId(slotId);
        if (slot == null) {
            throw new Exception("slot not found unable to update");
        }
        slot.setDoctor(slot.getDoctor());
        slot.setClinic(slot.getClinic());
        slot.setDateAndTime(slot.getDateAndTime());
        slot.setOccupied(slot.getOccupied());

        Slot newSlot = slotRepository.saveAndFlush(slot);
        logger.info("Updated slot" + newSlot.getDateAndTime() + newSlot.getClinic() +
                newSlot.getDoctor() + newSlot.getPatient());
        return newSlot;

    }

    public void deleteSlotById(long Id) throws Exception {
        boolean slotExists = slotRepository.existsById(Id);
        if (!slotExists) {
            throw new Exception("slot does not exist");
        }
        slotRepository.deleteById(Id);
        logger.info("Deleted slot");
    }
}
