package springServer.databaseOperations;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import springServer.businessLogic.Place;
import springServer.users.User;
import com.mongodb.client.model.Filters;

import java.util.Date;


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
            e.printStackTrace();
            throw new CustomDatabaseException("Nie udało się dodać oferty do bazy", e.getCause());
        } finally {
            this.mongoClient.close();
        }
    }

    private void registerOrUpdateUserIfNeeded() {
        MongoCollection<Document> usersCollection = database.getCollection("Users");
        Document existingUser = usersCollection.find(Filters.eq("email", this.user.getEmail())).first();
        Document newUser = Document.parse(this.user.toJson().toString());
        if (existingUser == null) {
            usersCollection.insertOne(newUser);
        } else {
            if (!existingUser.equals(newUser)) {
                usersCollection.replaceOne(Filters.eq("email", this.user.getEmail()), newUser);
            }
        }
    }

    String getAllOffers() {
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
    }
}
