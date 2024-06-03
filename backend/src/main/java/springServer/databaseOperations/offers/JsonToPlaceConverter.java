package springServer.databaseOperations.offers;

import org.json.JSONObject;
import springServer.businessLogic.Flat;
import springServer.businessLogic.House;
import springServer.businessLogic.Place;

import java.time.LocalDate;

@Deprecated
public class JsonToPlaceConverter {


    public static void main(String[] args) {
        Flat exampleFlat = new Flat("Fiołkowa", 3, "Bydgoszcz", 900_000, "50-333", LocalDate.of(2026, 5, 8), 40, 5, 44, "444");
        House exampleHouse = new House("Fiołkowa", 3, "Bydgoszcz", 900_000, "50-333", LocalDate.of(2026, 5, 8), 40, 50, "Sieeema");


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
