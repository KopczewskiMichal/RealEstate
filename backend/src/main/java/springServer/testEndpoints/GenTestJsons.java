package springServer.testEndpoints;

import springServer.businessLogic.Flat;

import java.time.LocalDate;

public class GenTestJsons {
    public static void main(String[] args) {
        Flat flat1 = new Flat("Warszawska",
                10,
                "Warszawa",
                500000,
                "00-001",
                LocalDate.of(2024, 12, 31),
                "author123",
                65,
                20,
                5,
                "https://example.com/image1.jpg",
                "Piękne mieszkanie w centrum Warszawy");
        System.out.println(flat1.toJson());
    }
}
