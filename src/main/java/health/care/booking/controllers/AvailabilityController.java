package health.care.booking.controllers;


import health.care.booking.dto.AvailabilityDTO;
import health.care.booking.models.Availability;
import health.care.booking.services.AvailabilityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityServices availabilityServices;

    @PostMapping
    public ResponseEntity<Availability> setAvailability(@RequestBody AvailabilityDTO availabilityDTO) {

        Availability availability = availabilityServices.saveAvailability(availabilityDTO);

        return ResponseEntity.ok(availability);
    }
}
