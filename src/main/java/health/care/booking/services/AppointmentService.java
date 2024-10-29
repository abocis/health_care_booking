package health.care.booking.services;

import health.care.booking.models.Appointment;
import health.care.booking.respository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    // CREATE
    public Appointment createAppointment(Appointment appointment) {
        System.out.println("Appointment has been created");
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
}
