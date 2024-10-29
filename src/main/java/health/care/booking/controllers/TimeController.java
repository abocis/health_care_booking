package health.care.booking.controllers;


import health.care.booking.models.Appointment;
import health.care.booking.models.Time;
import health.care.booking.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/maketime")
public class TimeController {

    @Autowired
    TimeService timeService;

    // POST
    @PostMapping
    public ResponseEntity<Time> createTime(@RequestBody Time time) {
        Time createdTime = timeService.createTime(time);
        return ResponseEntity.ok(createdTime);
    }



    // PUT
    @PutMapping("/update")
    public ResponseEntity<Time> updateTime(
            @PathVariable String caregiverId,
            @PathVariable String time,
            @RequestBody Time updatedTime) {
        Time time = timeService.updateTime(timeId, updatedTime, caregiverId);
        return ResponseEntity.ok(time);
    }



}
