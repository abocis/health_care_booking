package health.care.booking.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "time")
public class TimeSlots {

    @Id
    private String id;

    @DBRef
    private User caregiverId;

    private LocalTime time;



    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(User caregiverId) {
        this.caregiverId = caregiverId;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
