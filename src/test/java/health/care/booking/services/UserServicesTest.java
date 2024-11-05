package health.care.booking.services;

import health.care.booking.Enums.Role;
import health.care.booking.models.User;
import health.care.booking.respository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")  // Activate the test profile
@TestPropertySource(properties = {
        "jwt.secret=hfaiehfisehfosndfejndfeswljrfeowfnjehwbewios4ngvhtrwglp4rkledf",
        "jwt.expirationMs=36000000"
})
public class UserServicesTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

    }

    //TEST 1 SKAPA REGISTER USER
    @Test
    public void testCreateUser_Success(){
        User user = new User();
        user.setFirstName("Abou");
        user.setLastName("CISSE");
        user.setEmail("abou@cisse.com");
        user.setPassword("password");
        user.setRoles(Set.of(Role.USER));


        User saveUser = new User();
        saveUser.setId("1");
        saveUser.setFirstName(user.getFirstName());
        saveUser.setLastName(user.getLastName());
        saveUser.setEmail(user.getEmail());
        saveUser.setPassword(user.getPassword());
        saveUser.setRoles(Set.of(Role.USER));


        when(userRepository.save(user)).thenReturn(saveUser);

        //Act
        User result = userService.registerUser(user);

        assertNotNull(result.getId(), "saved user should have an id");
        assertEquals("Abou", result.getFirstName(), "first name should be same");
        assertEquals("CISSE", result.getLastName(), "last name should be same");
        assertEquals("abou@cisse.com", result.getEmail(), "email should be same");
        assertEquals("password", result.getPassword(), "password should be same");
        assertEquals(user.getRoles(), result.getRoles(), "roles should be same");

        verify(userRepository, times(1)).save(user);

    }
    // test create user not succes email already exist
    @Test
    public void testCreateUser_notSuccess_emailExist(){
        //arrange
        User user = new User();
        user.setFirstName("Abou");
        user.setLastName("CISSE");
        user.setEmail("abou@cisse.com");
        user.setPassword("password");
        user.setRoles(Set.of(Role.USER));

        //let's  say email already exist
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        //ACT
        boolean emailExists = userService.existsByEmail(user.getEmail());

        //Assert
        assertTrue(emailExists, "Email should exist hejhej ");

        verify(userRepository, never()).save(any(User.class)); //save is never called
    }



    @Test
    //test creste  user not exist username already exist
    public void testCreateUser_notSuccess_usernameExist(){

        User user = new User();
        user.setFirstName("Abou");
        user.setLastName("CISSE");
        user.setEmail("abou@cisse.com");
        user.setPassword("password");
        user.setRoles(Set.of(Role.USER));


        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        //ACT
        boolean usernameExists = userService.existsByUsername(user.getUsername());


        //assert
        assertTrue(usernameExists, "Username should exist ");

        verify(userRepository, never()).save(any(User.class));
    }


}
