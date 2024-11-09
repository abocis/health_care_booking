package health.care.booking.services;

import health.care.booking.dto.AvailabilityDTO;
import health.care.booking.models.Availability;
import health.care.booking.models.User;
import health.care.booking.respository.AvailabilityRepository;
import health.care.booking.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServices {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private UserRepository userRepository;


    public Availability saveOrUpdateAvailability(AvailabilityDTO availabilityDTO) {

        User caregiver = userRepository.findById(availabilityDTO.getCaregiverId())
                .orElseThrow(() -> new IllegalArgumentException("Caregiver not found"));


        Availability availability = new Availability();
        availability.setCaregiverId(caregiver);


        Optional<Availability> existingAvailability = availabilityRepository.findByCaregiverId(availabilityDTO.getCaregiverId());
        if (existingAvailability.isPresent()) {
            availability = existingAvailability.get();
            availability.getAvailableSlots().addAll(availabilityDTO.getAvailableSlots());
        } else {
            availability.setAvailableSlots(availabilityDTO.getAvailableSlots());
        }

        return availabilityRepository.save(availability);
    }


    public List<Availability> getAllAvailability() {
        return availabilityRepository.findAll();
    }
}
