package health.care.booking.controllers;

import health.care.booking.dto.AvailabilityDTO;
import health.care.booking.models.Availability;
import health.care.booking.services.AvailabilityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityServices availabilityServices;

    // Skapa eller uppdatera tillgänglighet med nya tider
    @PostMapping
    public ResponseEntity<Availability> setAvailability(@RequestBody AvailabilityDTO availabilityDTO) {
        Availability availability = availabilityServices.saveOrUpdateAvailability(availabilityDTO);
        return ResponseEntity.ok(availability);
    }

    // Hämta alla tillgängligheter
    @GetMapping("/all")
    public ResponseEntity<List<Availability>> getAllAvailability() {
        List<Availability> availabilities = availabilityServices.getAllAvailability();
        return ResponseEntity.ok(availabilities);
    }
}
