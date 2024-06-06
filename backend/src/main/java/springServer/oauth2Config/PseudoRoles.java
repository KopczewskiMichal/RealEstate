package springServer.oauth2Config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public interface PseudoRoles {
    String getMongoUri();

    default boolean isAdmin() {
        // Możemy sobie pozolić na brak sprawdzania typów bo program wcześniej sprawdza autentykacje
        OAuth2User oauthUser = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = oauthUser.getAttribute("email");
        System.out.println(email);
        List<String> roles = getRolesFromDb(email);
        System.out.println(roles);
        return roles.contains(Roles.ADMIN.toString());
    }

    private List<String> getRolesFromDb(String email) {
        MongoClient mongoClient = MongoClients.create(getMongoUri());
        MongoDatabase database = mongoClient.getDatabase("RealEstate");
        MongoCollection<Document> usersCollection = database.getCollection("Users");
        Document existingUser = usersCollection.find(eq("email", email)).first();
        mongoClient.close();
        if (existingUser == null) {
            return new ArrayList<>();
        } else {
            List<String> roles = (List<String>) existingUser.get("roles");
            return roles != null ? roles : new ArrayList<>();
        }
    }
}
