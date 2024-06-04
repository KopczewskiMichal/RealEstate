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
                "https://www.instagram.com/p/C6n4e6Rq3vK/",
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
                "https://www.google.com/imgres?q=house&imgurl=https%3A%2F%2Fimages.pexels.com%2Fphotos%2F106399%2Fpexels-photo-106399.jpeg%3Fcs%3Dsrgb%26dl%3Dpexels-binyaminmellish-106399.jpg%26fm%3Djpg&imgrefurl=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fhouse%2F&docid=w_edFuvJNI2ApM&tbnid=kdENxlNldMG6sM&vet=12ahUKEwiMtcOs6sGGAxWDB9sEHWFJF8oQM3oECB4QAA..i&w=5408&h=3605&hcb=2&ved=2ahUKEwiMtcOs6sGGAxWDB9sEHWFJF8oQM3oECB4QAA",
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
