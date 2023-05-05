package develhope.DClinic.domain.DTO;

public class BookingDTO {
    private long patientId;
    private long slotId;
    private String notes;
    //todo medicalServices


    public BookingDTO() {
    }

    public BookingDTO(long patientId, long slotId, String notes) {
        this.patientId = patientId;
        this.slotId = slotId;
        this.notes = notes;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getSlotId() {
        return slotId;
    }

    public void setSlotId(long slotId) {
        this.slotId = slotId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
