package springServer.users;

import net.minidev.json.JSONObject;

public class User {
    private final String username;
    private final String email;
    private final String profilePictureUrl;

    public String getEmail() {
        return email;
    }

    public User(String username, String email, String profilePictureUrl) {
        this.username = username;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("email", email);
        jsonObject.put("profilePictureUrl", profilePictureUrl);
        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        User user = (User) o;
        return email == user.email; // Wcześniej sprawdzone czy nie porównujemy nulli
    }

    @Override
    public int hashCode () {
        // wszystkie dane poza mailem użytkownik może zmieniać w serwerze autentykacji
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "name:  " + username + "\n" +
                "email:  " + email + "\n" +
                "profilePictureUrl: " + profilePictureUrl + "\n";
    }
}
