package health.care.booking.Controller;

public class AuthControllerTest {

   /* @Mock
    private UserService userService;
    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_UserNameAlreadyExists() {
        //Given
        RegisterRequest request = new RegisterRequest("abou", "cisse"
                , "abou@cisse.com"
                , "UserAbou"
                , "password"
                , Set.of(Role.USER));
        //mock that username already exists
        when(userService.existsByUsername(request.getUsername())).thenReturn(true);

        //when
        ResponseEntity<?> response = authController.register(request);

        //then
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Username is already exist", response.getBody()); // this should match the message in the controller or the test failed

        //verify the service method is called once
        verify(userService, times(1)).existsByUsername(request.getUsername());

        //ensure register user is never called since username already exist
        verify(userService, never()).registerUser(any(User.class));
    }

    @Test
    void testRegister_EmailAlreadyExists() {
        //given
        RegisterRequest request = new RegisterRequest("abou", "cisse"
                , "abou@cisse.com"
                , "UserAbou"
                , "password"
                , Set.of(Role.USER));

        // mock that Email already esxist
        when(userService.existsByEmail(request.getEmail())).thenReturn(true);

        //when
        ResponseEntity<?> response = authController.register(request);

        //then
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Username or email is already exist", response.getBody());

        //verify the method is called once
        verify(userService, times(1)).existsByEmail(request.getEmail());
        // ensure register is never call
        verify(userService, never()).registerUser(any(User.class));


    }
    @Test
    void testRegister_success() {
        RegisterRequest request = new RegisterRequest("abou", "cisse"
                , "abou@cisse.com"
                , "UserAbou"
                , "password"
                , Set.of(Role.USER));
        //email and username should be unique
        when(userService.existsByUsername(request.getUsername())).thenReturn(false);
        when(userService.existsByEmail(request.getEmail())).thenReturn(false);

        ResponseEntity<?> response = authController.register(request);

        //assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        RegisterResponse registerResponse = (RegisterResponse) response.getBody();
        assertNotNull(registerResponse);
        assertEquals("UserAbou", registerResponse.getUsername());
        verify(userService, times(1)).existsByUsername(request.getUsername());
    }

*/



}
