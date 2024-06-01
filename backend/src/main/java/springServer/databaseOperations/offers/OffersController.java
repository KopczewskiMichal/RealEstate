package springServer.databaseOperations.offers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OffersController {
    @Value("${mongodb.uri}")
    private String mongoUri;

    @Deprecated // ! metoda jest bezsensowna
    @GetMapping("/check-db")
    public ResponseEntity<String> checkDb() {
        try (MongoClient mongoClient = MongoClients.create(mongoUri)) {
            MongoDatabase database = mongoClient.getDatabase("RealEstate");
            MongoCollection<Document> collection = database.getCollection("Offers");

            StringBuilder result = new StringBuilder();
            for (Document doc : collection.find()) {
                result.append(doc.toJson()).append("\n");
            }

            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // TODO: podpięcie logiki nieruchomości z labów javy
}
