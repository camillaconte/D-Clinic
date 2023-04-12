package develhope.DClinic.domain;

public class BookingDTO {

    private long patientId;
    private long slotId;
    // todo medicalService
    private String notes;

    public BookingDTO() {
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
