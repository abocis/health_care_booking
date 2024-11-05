package health.care.booking.dto;

import health.care.booking.Enums.Status;

import java.time.LocalDateTime;

public class AppointmentDTO {

    public String patientId;
    private String  caregiverId;
    private LocalDateTime dateTime;
    private Status status;

    //tom contructor
    public AppointmentDTO() {
    }

    public AppointmentDTO(String patientId, String caregiverId, LocalDateTime dateTime) {
        this.patientId = patientId;
        this.caregiverId = caregiverId;
        this.dateTime = dateTime;
    }


    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getCaregiverId() {
        return caregiverId;
    }


    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
