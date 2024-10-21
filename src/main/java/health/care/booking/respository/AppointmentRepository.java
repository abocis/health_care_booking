package health.care.booking.respository;

import health.care.booking.models.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends  MongoRepository<Appointment, String> {
    List<Appointment> findByPatientId(String patientId);
    List<Appointment> findByCaregiverId(String caregiverId);
    Optional<Appointment> findByIdAndPatientId(String id, String patientId);
}