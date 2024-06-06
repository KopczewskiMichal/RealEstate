package springServer.databaseOperations;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import springServer.businessLogic.Place;
import springServer.users.User;
import com.mongodb.client.model.Filters;

import java.time.LocalDate;
import java.util.Date;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;



// * Z założenia obiekt tej klasy jest krótkotrwały, mamy nadzieję że baza nie padnie w trakciejego istnienia
final class OfferIntoDb {
    // * Nie da się pobrać mongouri za pomocą @Value spoza komponentu Springa
    private User user;
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    OfferIntoDb(String mongoUri) {
        mongoClient = MongoClients.create(mongoUri);
        database = mongoClient.getDatabase("RealEstate");
    }

    void insertOfferIntoDb (Place place, User user) throws CustomDatabaseException{
        this.user = user;
        try {
            registerOrUpdateUserIfNeeded();
            MongoCollection<Document> offersCollection = database.getCollection("Offers");
            Document newOffer = Document.parse(place.toJson().toString());
            offersCollection.insertOne(newOffer);
        } catch (Exception e) {
            throw new CustomDatabaseException("Nie udało się dodać oferty do bazy", e.getCause());
        } finally {
            this.mongoClient.close();
        }
    }

    private void registerOrUpdateUserIfNeeded() {
        MongoCollection<Document> usersCollection = database.getCollection("Users");
        Document existingUser = usersCollection.find(eq("email", this.user.getEmail())).first();
        Document newUser = Document.parse(this.user.toJson().toString());
        if (existingUser == null) {
            usersCollection.insertOne(newUser);
        } else {
            if (!existingUser.equals(newUser)) {
                usersCollection.replaceOne(eq("email", this.user.getEmail()), newUser);
            }
        }
    }

    String getAllOffers() {
        try {
            MongoCollection<Document> offersCollection = database.getCollection("Offers");
            StringBuilder result = new StringBuilder();
            Date now = new Date();
            for (Document doc : offersCollection.find(Filters.lt("deadline", now.toString()))) {
                result.append(doc.toJson()).append("\n");
            }
            if (result.isEmpty()) {
                result.append("No offers found");
            }
            return result.toString();
        } finally {
            mongoClient.close();
        }
    }

    String getMyOffers(String userEmail) {
        try {
            MongoCollection<Document> offersCollection = database.getCollection("Offers");
            StringBuilder result = new StringBuilder();
            for (Document doc : offersCollection.find(eq("authorEmail", userEmail))) {
                result.append(doc.toJson()).append("\n");
            }
            if (result.isEmpty()) {
                result.append("No offers found");
            }
            return result.toString();
        } finally {
            mongoClient.close();
        }
    }

    void deleteOfferAsAdmin(String offerId) {
        try {
            MongoCollection<Document> usersCollection = database.getCollection("Offers");
            usersCollection.deleteOne(eq("offerId", offerId));
        } finally {
            mongoClient.close();
        }
    }

    void deleteOwnOffer(String offerId) {
        try {
            OAuth2User oauthUser = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String email = oauthUser.getAttribute("email");
            MongoCollection<Document> usersCollection = database.getCollection("Offers");
            UpdateResult updateResult = usersCollection.updateOne(
                    and(eq("offerId", offerId), eq("authorEmail", email)),
                    set("deadline", LocalDate.now().toString()));
            System.out.println(updateResult);
        } finally {
            mongoClient.close();
        }
    }
}
