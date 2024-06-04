package springServer.databaseOperations.offers;

import org.json.JSONObject;
import springServer.businessLogic.Flat;
import springServer.businessLogic.House;
import springServer.businessLogic.Place;

import java.time.LocalDate;

@Deprecated
public class JsonToPlaceConverter {


    public static void main(String[] args) {
        Flat exampleFlat = new Flat(
                "ul. Główna 123",
                5,
                "Warszawa",
                250000,
                "01-234",
                LocalDate.of(2024, 6, 30),
                "user123",
                80,
                2,
                3,
                "https://example.com/flat1.jpg",
                "Przestronne mieszkanie z widokiem na park."
        );


        House exampleHouse = new House(
                "ul. Fiołkowa 123",
                56,
                "Zielona Góra",
                1_000_000,
                "01-234",
                LocalDate.of(2024, 8, 15),
                450,
                "80",
                "sieeeeema",
                "Fajny taki domek",
                1500

        );

        JSONObject flatJsonObject = exampleFlat.toJson();
        JSONObject houseJsonObject = exampleHouse.toJson();
        try {
            Place flat = Place.fromJson(flatJsonObject);
            System.out.println(flat);

            Place house = Place.fromJson(houseJsonObject);
            System.out.println(house);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
