package health.care.booking.services;


import health.care.booking.Enums.Role;
import health.care.booking.dto.AvailabilityDTO;
import health.care.booking.models.Availability;
import health.care.booking.models.User;
import health.care.booking.respository.AvailabilityRepository;
import health.care.booking.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityServices {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private UserRepository userRepository;


    public Availability saveAvailability(AvailabilityDTO availabilityDTO) {

        //check
        User caregiver = userRepository.findById(availabilityDTO.getCaregiverId())
                .orElseThrow(() -> new IllegalArgumentException("caregiver not found"));

        // test code!! see if it worked Check if the caregiver has the ADMIN role
        if (!caregiver.getRoles().contains(Role.ADMIN)) {
            throw new IllegalArgumentException("User is not an admin and cannot set availability.");
        }

        //create availability and set the caregiver and available slots
        Availability availability = new Availability();

        availability.setCaregiverId(caregiver);
        availability.setAvailableSlots(availabilityDTO.getAvailableSlots());

        return availabilityRepository.save(availability);
    }
}
