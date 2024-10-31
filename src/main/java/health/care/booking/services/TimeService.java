package health.care.booking.services;

import health.care.booking.models.Time;
import health.care.booking.respository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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


    // Get all
    public List<Time> getAllTimes() {
        return timeRepository.findAll();
    }

    // delete by id
    public void deleteTime(String timeId, String userId) {
        Time time = timeRepository.findById(timeId)
                .orElseThrow(() -> new RuntimeException("Time not found"));

        // validation of user to delete
        if (!time.getCaregiverId().getId().equals(userId) && !time.getCaregiverId().getId().equals(userId)) {
            throw new RuntimeException("Only the patient or caregiver can delete this time slot");
        }

        timeRepository.delete(time);
    }

    // get by id
    public Time getTimeById(String id) {
        return timeRepository.findById(id).get();
    }


}
