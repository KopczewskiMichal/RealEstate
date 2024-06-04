package springServer.businessLogic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.time.LocalDate;

public final class House extends Place {
    @JsonProperty("lotSize")
    private final int lotSize;

    @JsonCreator
    public House(
            @JsonProperty("street") String street,
            @JsonProperty("number") int number,
            @JsonProperty("city") String city,
            @JsonProperty("price") int price,
            @JsonProperty("postCode") String postCode,
            @JsonProperty("deadline") LocalDate deadline,
            @JsonProperty("area") int size,
            @JsonProperty("authorId") String authorId,
            @JsonProperty("imageUrl") String imageUrl,
            @JsonProperty("description") String description,
            @JsonProperty("lotSize") int lotSize
    ) {
        super(street, number, city, price, postCode, deadline, size, authorId, imageUrl, description);
        this.lotSize = lotSize;
    }


    @Override
    public JSONObject toJson () {
        JSONObject jsonObject = super.toJson();
        jsonObject.put("lotSize", lotSize);
        jsonObject.put("type", "house");
        return jsonObject;
    }

    @Override
    public String toString() {
        return this.toJson().toString();
    }
}
