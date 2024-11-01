package health.care.booking.controllers;

import health.care.booking.models.TimeSlots;
import health.care.booking.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timeslot")
public class TimeController {

    @Autowired
    private TimeService timeService;

    // create POST
    @PostMapping("/create")
    public ResponseEntity<TimeSlots> createTime(@RequestBody TimeSlots time) {
        TimeSlots createdTime = timeService.createTime(time);
        return ResponseEntity.ok(createdTime);
    }

    // Update
    @PutMapping("/update/{caregiverId}/{timeId}")
    public ResponseEntity<TimeSlots> updateTime(
            @PathVariable String caregiverId,
            @PathVariable String timeId,
            @RequestBody TimeSlots updatedTime) {
        TimeSlots updatedTimeResult = timeService.updateTime(timeId, updatedTime, caregiverId);
        return ResponseEntity.ok(updatedTimeResult);
    }

    // Get all
    @GetMapping("/all")
    public List<TimeSlots> getAllTimes() {
        return timeService.getAllTimes();
    }

    // Delete time by id
    @DeleteMapping("/{timeId}")
    public ResponseEntity<Void> deleteTime(@PathVariable String timeId, @RequestParam String userId) {
        timeService.deleteTime(timeId, userId);
        return ResponseEntity.noContent().build();
    }

    // Get time by id
    @GetMapping("/{timeId}")
    public ResponseEntity<TimeSlots> getTimeById(@PathVariable String timeId) {
        TimeSlots time = timeService.getTimeById(timeId);
        return ResponseEntity.ok(time);
    }
}
