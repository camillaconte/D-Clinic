package develhope.DClinic.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class SlotDTO {

     long clinicId;
     long doctorId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
     LocalDateTime dateAndTime;

    public SlotDTO() {
    }

    public SlotDTO( long clinicId, long doctorId, LocalDateTime dateAndTime) {

        this.clinicId = clinicId;
        this.doctorId = doctorId;
        this.dateAndTime = dateAndTime;
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
