package health.care.booking.controllers;

import health.care.booking.dto.AppointmentDTO;
import health.care.booking.models.Appointment;
import health.care.booking.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    // POST
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.ok(appointment);
    }

/**
    // GET all appointments for patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getUserAppointments(@PathVariable String patientId) {
        List<Appointment> appointments = appointmentService.getUserAppointments(patientId);
        return ResponseEntity.ok(appointments);
    }

    // GET a single appointment for a patient
    @GetMapping("/patient/{patientId}/appointment/{appointmentId}")
    public ResponseEntity<Appointment> getUserSingleAppointment(
            @PathVariable String patientId,
            @PathVariable String appointmentId) {
        return appointmentService.getUserSingleAppointment(patientId, appointmentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT update an appointment caregiver/admin only
    @PutMapping("/caregiver/{caregiverId}/appointment/{appointmentId}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable String caregiverId,
            @PathVariable String appointmentId,
            @RequestBody Appointment updatedAppointment) {
        Appointment appointment = appointmentService.updateAppointment(appointmentId, updatedAppointment, caregiverId);
        return ResponseEntity.ok(appointment);
    }

    // DELETE Cancel an appointment (User or Admin)
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String appointmentId, @RequestParam String userId) {
        appointmentService.deleteAppointment(appointmentId, userId);
        return ResponseEntity.noContent().build();
    }

    */
}
