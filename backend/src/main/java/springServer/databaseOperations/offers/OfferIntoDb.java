package springServer.databaseOperations.offers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import springServer.businessLogic.Place;
import springServer.databaseOperations.CustomDatabaseException;


// * Z założenia obiekt tej klasy jest krótkotrwały, mamy nadzieję że baza nie padnie w trakcie wykonywania jego roboty
final class OfferIntoDb {
    @Value("${mongodb.uri}")
    private String mongoUri;

    private final Place place;
    private final String authorEmail;

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    OfferIntoDb(Place place, String authorEmail) throws CustomDatabaseException{
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

//    public boolean isOfferInDatabase(JSONObject jsonObject) {
//        jsonObject.remove("creationDateTime"); // Nieistotne w rozumieniu metody equals
//        jsonObject.remove("offerId"); // Serwer generuje to przy tworzeniu oferty, więc nigdy w bazie nie będzie 2 takich samych
//        Document query = Document.parse(jsonObject.toString());
//        long count = collection.countDocuments(query);
//        return count > 0;
//    }




    void insertIntoDb () throws RuntimeException {
    }
}
