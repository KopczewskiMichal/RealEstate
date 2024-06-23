package springServer.oauth2Config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.bson.Document;
import springServer.users.User;


import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public interface PseudoRoles {
    String getMongoUri();

    default boolean isAdmin() {
        // Możemy sobie pozolić na brak sprawdzania typów bo program wcześniej sprawdza autentykacje
        OAuth2User oauthUser = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = oauthUser.getAttribute("email");
        List<Roles> roles = getRolesFromDb(email);
        System.out.println(roles);
        return roles.contains(Roles.ADMIN);
    }

    private List<Roles> getRolesFromDb(String email) {
        MongoClient mongoClient = MongoClients.create(getMongoUri());
        MongoDatabase database = mongoClient.getDatabase("RealEstate");
        MongoCollection<Document> usersCollection = database.getCollection("Users");
        Document existingUser = usersCollection.find(eq("email", email)).first();
        User user = User.fromJson(new JSONObject(existingUser));
        mongoClient.close();
        if (existingUser == null) {
            return new ArrayList<>();
        } else {
            return user.getRoles();
        }
    }
}
