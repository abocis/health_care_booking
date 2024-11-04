package health.care.booking.services;

import health.care.booking.Enums.Status;
import health.care.booking.dto.AppointmentDTO;
import health.care.booking.models.Appointment;
import health.care.booking.models.Availability;
import health.care.booking.models.User;
import health.care.booking.respository.AppointmentRepository;
import health.care.booking.respository.AvailabilityRepository;
import health.care.booking.respository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AvailabilityRepository availabilityRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAppointment() {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setCaregiverId("caregiver123");
        appointmentDTO.setPatientId("patient123");
        appointmentDTO.setDateTime(LocalDateTime.now());

        Availability availability = new Availability();
        availability.setAvailableSlots(new ArrayList<>(Collections.singletonList(appointmentDTO.getDateTime())));
        when(availabilityRepository.findByCaregiverId(anyString())).thenReturn(Optional.of(availability));

        User caregiver = new User();
        caregiver.setId("caregiver123");
        User patient = new User();
        patient.setId("patient123");
        when(userRepository.findById("caregiver123")).thenReturn(Optional.of(caregiver));
        when(userRepository.findById("patient123")).thenReturn(Optional.of(patient));

        Appointment appointment = new Appointment();
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment result = appointmentService.createAppointment(appointmentDTO);

        assertNotNull(result);
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
        verify(availabilityRepository, times(1)).save(any(Availability.class));
    }

    @Test
    void testGetUserAppointments() {
        String patientId = "patient123";
        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());
        when(appointmentRepository.findByPatientId(anyString())).thenReturn(appointments);

        ArrayList<Appointment> result = appointmentService.getUserAppointments(patientId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(appointmentRepository, times(1)).findByPatientId(patientId);
    }

    @Test
    void testGetUserSingleAppointment_Found() {
        String patientId = "patient123";
        String appointmentId = "appointment123";
        Appointment appointment = new Appointment();
        when(appointmentRepository.findByIdAndPatientId(anyString(), anyString())).thenReturn(Optional.of(appointment));

        Optional<Appointment> result = appointmentService.getUserSingleAppointment(patientId, appointmentId);

        assertTrue(result.isPresent());
        assertEquals(appointment, result.get());
    }

    @Test
    void testGetUserSingleAppointment_NotFound() {
        String patientId = "patient123";
        String appointmentId = "appointment123";
        when(appointmentRepository.findByIdAndPatientId(anyString(), anyString())).thenReturn(Optional.empty());

        Optional<Appointment> result = appointmentService.getUserSingleAppointment(patientId, appointmentId);

        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteAppointment_Success() {
        String appointmentId = "appointment123";
        String userId = "user123";
        Appointment appointment = new Appointment();
        User patient = new User();
        patient.setId(userId);
        User caregiver = new User();
        caregiver.setId(userId);
        appointment.setPatientId(patient);
        appointment.setCaregiverId(caregiver);
        when(appointmentRepository.findById(anyString())).thenReturn(Optional.of(appointment));

        appointmentService.deleteAppointment(appointmentId, userId);

        verify(appointmentRepository, times(1)).delete(appointment);
    }

    @Test
    void testDeleteAppointment_Unauthorized() {
        String appointmentId = "appointment123";
        String userId = "user123";
        Appointment appointment = new Appointment();
        User patient = new User();
        patient.setId("differentUserId");
        appointment.setPatientId(patient);
        User caregiver = new User();
        caregiver.setId("differentCaregiverId");
        appointment.setCaregiverId(caregiver);
        when(appointmentRepository.findById(anyString())).thenReturn(Optional.of(appointment));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            appointmentService.deleteAppointment(appointmentId, userId);
        });

        assertEquals("Only patient or caregiver can cancel this appointment", exception.getMessage());
    }

    @Test
    void testUpdateAppointment_Success() {
        String appointmentId = "appointment123";
        String caregiverId = "caregiver123";
        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setDateTime(LocalDateTime.now());
        updatedAppointment.setStatus(Status.SCHEDULED);

        Appointment existingAppointment = new Appointment();
        User caregiver = new User();
        caregiver.setId(caregiverId);
        existingAppointment.setCaregiverId(caregiver);
        when(appointmentRepository.findById(anyString())).thenReturn(Optional.of(existingAppointment));

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(updatedAppointment);

        Appointment result = appointmentService.updateAppointment(appointmentId, updatedAppointment, caregiverId);

        assertNotNull(result);
        assertEquals(updatedAppointment.getDateTime(), result.getDateTime());
        assertEquals(updatedAppointment.getStatus(), result.getStatus());
    }

    @Test
    void testUpdateAppointment_Unauthorized() {
        String appointmentId = "appointment123";
        String caregiverId = "caregiver123";
        Appointment existingAppointment = new Appointment();
        User differentCaregiver = new User();
        differentCaregiver.setId("differentCaregiverId");
        existingAppointment.setCaregiverId(differentCaregiver);
        when(appointmentRepository.findById(anyString())).thenReturn(Optional.of(existingAppointment));

        Appointment updatedAppointment = new Appointment();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            appointmentService.updateAppointment(appointmentId, updatedAppointment, caregiverId);
        });

        assertEquals("Caregiver can update this appointment", exception.getMessage());
    }
}
