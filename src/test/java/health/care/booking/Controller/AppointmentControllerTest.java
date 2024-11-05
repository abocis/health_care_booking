package health.care.booking.Controller;

class AppointmentControllerTest {
/*
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
    }*/
}
