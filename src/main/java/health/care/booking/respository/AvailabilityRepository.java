package health.care.booking.respository;

import health.care.booking.models.Availability;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AvailabilityRepository extends MongoRepository<Availability, String> {

    Optional<Availability> findByCaregiverId(String caregiverId);
}
