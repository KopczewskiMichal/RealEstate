package springServer.businessLogic;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Flat.class, name = "flat"),
        @JsonSubTypes.Type(value = House.class, name = "house")
})
sealed abstract public class Place permits House, Flat {
    @JsonProperty("offerId")
    protected final String offerId = UUID.randomUUID().toString();
    @JsonProperty("authorEmail")
    protected final String authorEmail;

    @JsonProperty("street")
    protected final String street;

    @JsonProperty("number")
    protected final int number;

    @JsonProperty("city")
    protected final String city;

    @JsonProperty("price")
    protected final int price;

    @JsonProperty("postCode")
    protected final String postCode;

    @JsonProperty("deadline")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected final LocalDate deadline;

    @JsonProperty("area")
    protected final int area;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("imageUrl")
    protected String imageUrl;

    @Override
    public String toString() {
        return this.toJson().toString();
    }

    @JsonProperty("creationDateTime")
    protected LocalDateTime creationDateTime = LocalDateTime.now();

    // TODO walidacja czy deadline jest czasem przyszłym
    @JsonCreator
    public Place(
            @JsonProperty("street") String street,
            @JsonProperty("number") int number,
            @JsonProperty("city") String city,
            @JsonProperty("price") int price,
            @JsonProperty("postCode") String postCode,
            @JsonProperty("deadline") LocalDate deadline,
            @JsonProperty("area") int area,
            @JsonProperty("authorEmail") String authorEmail,
            @JsonProperty("imageUrl") String imageUrl,
            @JsonProperty("description") String description) {

        this.street = street;
        this.number = number;
        this.city = city;
        this.price = price;
        this.postCode = postCode;
        this.deadline = deadline;
        this.area = area;
        this.authorEmail = authorEmail;
        this.imageUrl = imageUrl;
        this.description = description;
    }
    public JSONObject toJson () {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("offerId", offerId);
        jsonObject.put("authorEmail", authorEmail);
        jsonObject.put("street", street);
        jsonObject.put("number", number);
        jsonObject.put("city", city);
        jsonObject.put("price", price);
        jsonObject.put("postCode", postCode);
        jsonObject.put("deadline", deadline.toString());
        jsonObject.put("creationDateTime", creationDateTime.toString());
        jsonObject.put("area", area);
        jsonObject.put("imageUrl", imageUrl);
        jsonObject.put("description", description);
        return jsonObject;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return number == place.number && price == place.price && area == place.area && Objects.equals(authorEmail, place.authorEmail) && Objects.equals(street, place.street) && Objects.equals(city, place.city) && Objects.equals(postCode, place.postCode) && Objects.equals(offerId, place.offerId) && Objects.equals(creationDateTime, place.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorEmail, street, number, city, price, postCode, area, offerId, creationDateTime);
    }
}
