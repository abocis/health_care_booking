package health.care.booking.services;


import health.care.booking.dto.AvailabilityDTO;
import health.care.booking.models.Availability;
import health.care.booking.models.User;
import health.care.booking.respository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityServices {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public Availability saveAvailability(AvailabilityDTO availabilityDTO) {

        Availability availability = new Availability();
        availability.setCaregiverId(new User(availabilityDTO.getCaregiverId()));
        availability.setAvailableSlots(availabilityDTO.getAvailableSlots());

        return availabilityRepository.save(availability);
    }

}
