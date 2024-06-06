package springServer.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.minidev.json.JSONObject;
import springServer.oauth2Config.Roles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final String email;
    private final String profilePictureUrl;

    private final List<Roles> roles;

    public List<Roles> getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public User(String username, String email, String profilePictureUrl) {
        this.username = username;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
        this.roles = new ArrayList<>();
        this.roles.add(Roles.USER);
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("email", email);
        jsonObject.put("profilePictureUrl", profilePictureUrl);
        jsonObject.put("roles", roles);
        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        User user = (User) o;
        return email == user.email;
    }

    @Override
    public int hashCode () {
        // wszystkie dane poza mailem użytkownik może zmieniać w serwerze autentykacji
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                ", roles=" + roles +
                '}';
    }

    public static User fromJson(org.json.JSONObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(jsonObject.toString(), User.class);
        } catch (MismatchedInputException e) {
            throw new IllegalArgumentException("JSON is missing required fields or has incorrect types: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("Error while creating object from JSON: " + e.getMessage(), e);
        }
    }
}
