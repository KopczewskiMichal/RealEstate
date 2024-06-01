package springServer.businessLogic;

import org.json.JSONObject;

import java.time.LocalDate;

public final class Flat extends Place {
    private final int flatNumber;
    private final int floor;

    public int getFloor() {
        return floor;
    }

    public Flat(String street, int number, String city, int price, String postCode, LocalDate deadline, int size, int houseNumber, int flatNumber, int floor,  String authorId) {
        super(street, number, city, price, postCode, deadline, size, houseNumber, authorId);
        this.flatNumber = flatNumber;
        this.floor = floor;
    }

    @Override
    public JSONObject toJson () {
        JSONObject jsonObject = super.toJson();
        jsonObject.put("flatNumber", flatNumber);
        jsonObject.put("floor", floor);
        return jsonObject;
    }

    @Override
    public String toString() {
        return "Mieszkanie nr. " + this.flatNumber + "\n" +
                "Piętro nr: " + this.floor + "\n" +
                super.toString();
    }
}
