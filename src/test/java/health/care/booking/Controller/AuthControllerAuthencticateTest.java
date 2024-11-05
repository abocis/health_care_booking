package health.care.booking.Controller;

public class AuthControllerAuthencticateTest {
/*

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserService userService;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
   void testLogin_Success() throws Exception {
        //Arrange
        AuthRequest request = new AuthRequest();
        request.setUsername("abocis");
        request.setPassword("password");

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("abocis");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn("jwtToken");


        //mock user
        User mockUser = mock(User.class);
        mockUser.setUsername("abocis");
        mockUser.setRoles(Set.of(Role.USER));

        when(userService.findByUsername("abocis")).thenReturn(mockUser);


        //Act
        ResponseEntity<?> responseEntity = authController.login(request, response);

        //assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        AuthResponse authResponse = (AuthResponse) responseEntity.getBody();
        assertNotNull(authResponse);
        assertEquals("Login successful", authResponse.getJwtToken());

        verify(response).addHeader(eq(HttpHeaders.SET_COOKIE), contains("jwt"));

    }

    @Test
    void testLogin_Failure() {
        // Arrange
        AuthRequest request = new AuthRequest();
        request.setUsername("UserAbou");
        request.setPassword("wrongpassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        // Act
        ResponseEntity<?> responseEntity = authController.login(request, response);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Incorrect username or password", responseEntity.getBody());
    }

*/


}
