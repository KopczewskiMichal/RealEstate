package springServer.databaseOperations;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import springServer.businessLogic.Place;
import springServer.users.User;
import com.mongodb.client.model.Filters;


// * Z założenia obiekt tej klasy jest krótkotrwały, mamy nadzieję że baza nie padnie w trakciejego istnienia
final class OfferIntoDb {
    private final String mongoUri; // * Nie da się pobrać za pomocą @Value spoza komponentu Springa

    private User user;

    private final MongoClient mongoClient;
    private final MongoDatabase database;

    OfferIntoDb(String mongoUri) {
        this.mongoUri = mongoUri;
        System.out.println("Zaraz tworzymy połączenie do bazy danych." + mongoUri);
        mongoClient = MongoClients.create(mongoUri);
        System.out.println("Sieeema, zaraz łączymy sie z bazą w bazie xd");
        database = mongoClient.getDatabase("RealEstate");
        System.out.println("to mongouri" + mongoUri);
        System.out.println("database" + database);
    }

    void insertOfferIntoDb (Place place, User user) throws CustomDatabaseException{
        System.out.println(this.mongoUri);
        this.user = user;
        try {
            registerUserIfNeeded();
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

    private void registerUserIfNeeded () {
        System.out.println("Jestem w register ...");
        MongoCollection<Document> usersCollection = database.getCollection("Users");
        Document user = usersCollection.find(Filters.eq("email", this.user.getEmail())).first();
        if (user == null) {
            Document newUser = Document.parse(this.user.toJson().toString());
            usersCollection.insertOne(newUser);
        }
    }
}
