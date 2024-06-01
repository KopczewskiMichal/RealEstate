package springServer.businessLogic;

import org.json.JSONObject;

import java.time.LocalDate;

public final class House extends Place {
    private final int lotSize;

    public House(String street, int number, String city, int price, String postCode, LocalDate deadline, int size, int houseNumber, int lotSize, String authorId) {
        super(street, number, city, price, postCode, deadline, size, houseNumber, authorId);
        this.lotSize = lotSize;
    }

    @Override
    public JSONObject toJson () {
        JSONObject jsonObject = super.toJson();
        jsonObject.put("lotSize", lotSize);
        return jsonObject;
    }

    @Override
    public String toString() {
        return "Dom na działce o wielkości: " + this.lotSize + "\n" +
                super.toString();
    }
}
