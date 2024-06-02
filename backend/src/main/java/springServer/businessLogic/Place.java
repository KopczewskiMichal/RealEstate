package springServer.businessLogic;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonFormat;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Flat.class, name = "flat"),
        @JsonSubTypes.Type(value = House.class, name = "house")
})
sealed abstract public class Place permits House, Flat {
    protected final String authorId;
    protected final String street;
    protected final int number;
    protected final String city;
    protected final int price;
    protected final String postCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    protected final LocalDate deadline;
    protected final int area;
    protected final int houseNumber;
    @JsonIgnore
    protected LocalDateTime creationDateTime = LocalDateTime.now();
    public LocalDate getDeadline () {
        return deadline;
    }
    public String getCity() {
        return city;
    }
    public int getArea() {
        return area;
    }
    public int getPrice() {
        return price;
    }


    public Place(String street, int number, String city, int price, String postCode, LocalDate deadline, int area, int houseNumber, String authorId) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.price = price;
        this.postCode = postCode;
        this.deadline = deadline;
        this.area = area;
        this.houseNumber = houseNumber;
        this.authorId = authorId;
    }

    public JSONObject toJson () {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authorId", authorId);
        jsonObject.put("street", street);
        jsonObject.put("number", number);
        jsonObject.put("city", city);
        jsonObject.put("price", price);
        jsonObject.put("postCode", postCode);
        jsonObject.put("deadline", deadline.toString());
        jsonObject.put("creationDateTime", creationDateTime.toString());
        jsonObject.put("area", area);
        jsonObject.put("houseNumber", houseNumber);
        return jsonObject;
    }

    @Override
    public String toString() {
        return "Ulica: " + street + "\n" +
                "Numer domu: " + number + "\n" +
                "City: " + city + "\n" +
                "Price: " + price + "\n" +
                "PostCode: " + postCode + "\n" +
                "Oferta aktualna do: " + deadline + "\n" +
                "Size: " + area + "\n" +
                "HouseNumber: " + houseNumber + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return number == place.number && price == place.price && area == place.area && houseNumber == place.houseNumber && Objects.equals(authorId, place.authorId) && Objects.equals(street, place.street) && Objects.equals(city, place.city) && Objects.equals(postCode, place.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, street, number, city, price, postCode, area, houseNumber);
    }
}
