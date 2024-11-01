package health.care.booking.respository;

import health.care.booking.models.TimeSlots;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends MongoRepository<TimeSlots, String> {
}
