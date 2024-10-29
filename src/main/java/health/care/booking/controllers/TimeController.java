package health.care.booking.controllers;


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


    // Update
    @PutMapping("/update/{caregiverId}/{timeId}")
    public ResponseEntity<Time> updateTime(
            @PathVariable String caregiverId,
            @PathVariable String timeId,
            @RequestBody Time updatedTime) {
        Time updatedTimeResult = timeService.updateTime(timeId, updatedTime, caregiverId);
        return ResponseEntity.ok(updatedTimeResult);
    }




}
