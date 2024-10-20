package health.care.booking.services;

import health.care.booking.Enums.Role;
import health.care.booking.models.User;
import health.care.booking.respository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


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



}
