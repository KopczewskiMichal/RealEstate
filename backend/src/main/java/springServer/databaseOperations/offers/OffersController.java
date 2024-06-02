package springServer.databaseOperations.offers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OffersController {

    @PostMapping("/add-offer") // TODO: sprawdzenie roli użytkownika
    public ResponseEntity<String> addOffer (@RequestBody String body) {
        try {
        // TODO endpoint dodania oferty
        }
    }


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

}
