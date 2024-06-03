package springServer.databaseOperations.offers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONObject;
import springServer.businessLogic.Flat;
import springServer.businessLogic.House;
import springServer.businessLogic.Place;

import java.io.IOException;
import java.time.LocalDate;


public class JsonToPlaceConverter {

    public static Place fromJson(JSONObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(jsonObject.toString(), Place.class);
        } catch (MismatchedInputException e) {
            throw new IllegalArgumentException("JSON is missing required fields or has incorrect types: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("Error while creating object from JSON: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        Flat exampleFlat = new Flat("Fiołkowa", 3, "Bydgoszcz", 900_000, "50-333", LocalDate.of(2026, 5, 8), 40, 5, 44, "444");
        House exampleHouse = new House("Fiołkowa", 3, "Bydgoszcz", 900_000, "50-333", LocalDate.of(2026, 5, 8), 40, 50, "Sieeema");


        JSONObject flatJsonObject = exampleFlat.toJson();
        JSONObject houseJsonObject = exampleHouse.toJson();
        try {
            Place flat = fromJson(flatJsonObject);
            System.out.println(flat);

            Place house = fromJson(houseJsonObject);
            System.out.println(house);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
