package health.care.booking.Controller;

import health.care.booking.Enums.Role;
import health.care.booking.controllers.AuthController;
import health.care.booking.dto.AuthRequest;
import health.care.booking.dto.AuthResponse;
import health.care.booking.models.User;
import health.care.booking.services.UserService;
import health.care.booking.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthControllerAuthencticateTest {

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



}
