package health.care.booking.Controller;

import health.care.booking.controllers.AppointmentController;
import health.care.booking.dto.AppointmentDTO;
import health.care.booking.models.Appointment;
import health.care.booking.services.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAppointment() {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        Appointment appointment = new Appointment();
        when(appointmentService.createAppointment(any(AppointmentDTO.class))).thenReturn(appointment);

        ResponseEntity<Appointment> response = appointmentController.createAppointment(appointmentDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(appointment, response.getBody());
    }


    @Test
    void testGetUserSingleAppointment_Found() {
        String patientId = "patient123";
        String appointmentId = "appointment123";
        Appointment appointment = new Appointment();
        when(appointmentService.getUserSingleAppointment(anyString(), anyString())).thenReturn(Optional.of(appointment));

        ResponseEntity<Appointment> response = appointmentController.getUserSingleAppointment(patientId, appointmentId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(appointment, response.getBody());
    }

    @Test
    void testGetUserSingleAppointment_NotFound() {
        String patientId = "patient123";
        String appointmentId = "appointment123";
        when(appointmentService.getUserSingleAppointment(anyString(), anyString())).thenReturn(Optional.empty());

        ResponseEntity<Appointment> response = appointmentController.getUserSingleAppointment(patientId, appointmentId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateAppointment() {
        String caregiverId = "caregiver123";
        String appointmentId = "appointment123";
        Appointment updatedAppointment = new Appointment();
        when(appointmentService.updateAppointment(anyString(), any(Appointment.class), anyString())).thenReturn(updatedAppointment);

        ResponseEntity<Appointment> response = appointmentController.updateAppointment(caregiverId, appointmentId, updatedAppointment);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedAppointment, response.getBody());
    }

    @Test
    void testDeleteAppointment() {
        String appointmentId = "appointment123";
        String userId = "user123";
        doNothing().when(appointmentService).deleteAppointment(anyString(), anyString());

        ResponseEntity<Void> response = appointmentController.deleteAppointment(appointmentId, userId);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
