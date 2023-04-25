package develhope.DClinic.domain;

import java.time.LocalDateTime;

public class SlotDTO {
    private LocalDateTime dateAndTime;
    private long clinicId;
    private long doctorId;

    public SlotDTO() {
    }

    public SlotDTO(LocalDateTime dateAndTime, long clinicId, long doctorId) {
        this.dateAndTime = dateAndTime;
        this.clinicId = clinicId;
        this.doctorId = doctorId;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public long getClinicId() {
        return clinicId;
    }

    public void setClinicId(long clinicId) {
        this.clinicId = clinicId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }
}
