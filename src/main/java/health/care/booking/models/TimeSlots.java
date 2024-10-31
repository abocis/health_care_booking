package health.care.booking.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "time")
public class TimeSlots {

    @Id
    private String id;

    @DBRef
    private User caregiverId;

    private TimeSlots timeSlots;


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

    public TimeSlots getTime() {
        return timeSlots;
    }

    public void setTime(TimeSlots time) {
        this.timeSlots = time;
    }
}
