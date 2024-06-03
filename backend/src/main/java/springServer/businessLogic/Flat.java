package springServer.businessLogic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.time.LocalDate;

public final class Flat extends Place {
    private final int flatNumber;

    @Override
    public String toString() {
        return this.toJson().toString();
    }

    private final int floor;

    @JsonCreator
    public Flat(
            @JsonProperty("street") String street,
            @JsonProperty("number") int number,
            @JsonProperty("city") String city,
            @JsonProperty("price") int price,
            @JsonProperty("postCode") String postCode,
            @JsonProperty("deadline") LocalDate deadline,
            @JsonProperty("area") int size,
            @JsonProperty("flatNumber") int flatNumber,
            @JsonProperty("floor") int floor,
            @JsonProperty("authorId") String authorId
    ) {
        super(street, number, city, price, postCode, deadline, size, authorId);
        this.flatNumber = flatNumber;
        this.floor = floor;
    }

    @Override
    public JSONObject toJson () {
        JSONObject jsonObject = super.toJson();
        jsonObject.put("flatNumber", flatNumber);
        jsonObject.put("floor", floor);
        jsonObject.put("type", "flat");
        return jsonObject;
    }

}
