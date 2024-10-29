package health.care.booking.services;

import health.care.booking.models.Time;
import health.care.booking.respository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    // CREATE
    public Time createTime(Time time) {
        System.out.println("Available time has been created");

        return time;
    }


    // Update
    public Time updateTime(String timeId, Time updatedTime, String caregiverId) {
        Time time = timeRepository.findById(timeId)
                .orElseThrow(() -> new RuntimeException("Time not found"));

        if (!time.getCaregiverId().getId().equals(caregiverId)) {
            throw new RuntimeException("Caregiver cannot update this time");
        }

        time.setTime(updatedTime.getTime());

        return timeRepository.save(time);
    }




}
