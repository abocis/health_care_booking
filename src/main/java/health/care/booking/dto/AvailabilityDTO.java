package health.care.booking.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AvailabilityDTO {

    private String caregiverId;
    private List<LocalDateTime> availableSlots;

    public AvailabilityDTO() {
    }

    public AvailabilityDTO(List<LocalDateTime> availableSlots, String caregiverId) {
        this.availableSlots = availableSlots;
        this.caregiverId = caregiverId;
    }

    public String getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }

    public List<LocalDateTime> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<LocalDateTime> availableSlots) {
        this.availableSlots = availableSlots;
    }
}