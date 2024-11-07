package health.care.booking.services;

import health.care.booking.Enums.Status;
import health.care.booking.dto.AppointmentDTO;
import health.care.booking.models.Appointment;
import health.care.booking.models.Availability;
import health.care.booking.respository.AppointmentRepository;
import health.care.booking.respository.AvailabilityRepository;
import health.care.booking.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private UserRepository userRepository;

    // CREATE
    public Appointment createAppointment(AppointmentDTO appointmentDTO) {

        Availability availability = availabilityRepository.findByCaregiverId(appointmentDTO.getCaregiverId())
                .orElseThrow(()-> new RuntimeException("caregiver availability not found"));

        if (!availability.getAvailableSlots().contains(appointmentDTO.getDateTime())){
            throw new RuntimeException("selected time not available ");
        }

        //remove slots
        availability.getAvailableSlots().remove(appointmentDTO.getDateTime());
        availabilityRepository.save(availability);

        //create new appointement
        Appointment appointment = new Appointment();
        appointment.setCaregiverId(userRepository.findById(appointmentDTO.getCaregiverId())
                .orElseThrow(() -> new IllegalArgumentException("Caregiver not found with ID: " + appointmentDTO.getCaregiverId())));

        appointment.setPatientId(userRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + appointmentDTO.getPatientId())));

        appointment.setDateTime(appointmentDTO.getDateTime());
        appointment.setStatus(Status.SCHEDULED);

        return appointmentRepository.save(appointment);

    }

    // GET all appointments for patient
    public ArrayList<Appointment> getUserAppointments(String patientId) {
        return (ArrayList<Appointment>) appointmentRepository.findByPatientId(patientId);
    }

    // GET a specific appointment for patient
    public Optional<Appointment> getUserSingleAppointment(String patientId, String appointmentId) {
        return appointmentRepository.findByIdAndPatientId(appointmentId, patientId);
    }

    // DELETE a specific appointment by using id
    public void deleteAppointment(String appointmentId, String userId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        // Check if the user is either the patient or the caregiver
        if (!appointment.getPatientId().getId().equals(userId) && !appointment.getCaregiverId().getId().equals(userId)) {
            throw new RuntimeException("Only patient or caregiver can cancel this appointment");
        }

        appointmentRepository.delete(appointment);
    }

    // Update appointment
    public Appointment updateAppointment(String appointmentId, Appointment updatedAppointment, String caregiverId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (!appointment.getCaregiverId().getId().equals(caregiverId)) {
            throw new RuntimeException("Caregiver can update this appointment");
        }

        appointment.setDateTime(updatedAppointment.getDateTime());
        appointment.setStatus(updatedAppointment.getStatus());

        return appointmentRepository.save(appointment);
    }

    public List<Appointment>getAppointmentsbyCaregiverId(String caregiverId) {
        return appointmentRepository.findByCaregiverId(caregiverId);
    }


}
