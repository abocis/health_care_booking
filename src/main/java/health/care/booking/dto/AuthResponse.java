package health.care.booking.dto;

import health.care.booking.Enums.Role;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Set;

public class AuthResponse {

    @DBRef
    private String id;
    private String jwtToken;
    private String username;
    private Set<Role> roles;

    public AuthResponse() {
    }

    public AuthResponse(String id, String jwtToken, String username, Set<Role> roles) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.roles = roles;
        this.id = id;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

