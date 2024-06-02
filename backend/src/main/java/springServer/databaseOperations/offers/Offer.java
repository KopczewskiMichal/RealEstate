package springServer.databaseOperations.offers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import springServer.businessLogic.Place;
import springServer.databaseOperations.CustomDatabaseException;


// * Z założenia obiekt tej klasy jest krótkotrwały, mamy nadzieję że baza nie padnie w trakcie wykonywania jego roboty
final class Offer {
    @Value("${mongodb.uri}")
    private String mongoUri;

    private Place place;
    private String authorEmail;

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    Offer(Place place, String authorEmail) throws CustomDatabaseException{
        this.place = place;
        this.authorEmail = authorEmail;
        try {
            this.mongoClient = MongoClients.create(mongoUri);
            this.database = mongoClient.getDatabase("RealEstate");
            this.collection = database.getCollection("Offers");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomDatabaseException("Nie udało się nawiązać połączenia z bazą danych", e.getCause());
        }
    }

    public boolean isOfferInDatabase(JSONObject jsonObject) {
        jsonObject.remove("creationDateTime"); // Nieistotne w rozumieniu metody equals
        Document query = Document.parse(jsonObject.toString());
        long count = collection.countDocuments(query);
        return count > 0;
    }




    void insertIntoDb () throws RuntimeException {
        try {




        } catch (Exception e) {
             throw new CustomDatabaseException("Nie udało się nawiązać połączenia z bazą danych.", e.getCause());
        }
    }
}
