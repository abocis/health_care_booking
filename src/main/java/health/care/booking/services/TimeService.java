package health.care.booking.services;

import health.care.booking.models.Appointment;
import health.care.booking.models.Time;

public class TimeService {

    // CREATE
    public Appointment createTime(Time time) {
        System.out.println("Available time has been created");
        return timeRepository.save(time);
    }

}
