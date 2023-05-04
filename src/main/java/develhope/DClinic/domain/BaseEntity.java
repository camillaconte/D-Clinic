package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    private LocalDateTime creationDateTime;

    private LocalDateTime updateDateTime;

    public BaseEntity() {
        creationDateTime = LocalDateTime.now();
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
        this.updateDateTime = LocalDateTime.now();
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        this.updateDateTime = LocalDateTime.now();
    }
}
