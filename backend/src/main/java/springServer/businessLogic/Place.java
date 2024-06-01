package springServer.businessLogic;

import org.json.JSONObject;

import java.time.LocalDate;

sealed abstract class Place permits House, Flat {
    protected final String authorId;
    protected final String street;
    protected final int number;
    protected final String city;
    protected final int price;
    protected final String postCode;
    protected final LocalDate deadline;
    protected final int area;
    protected final int houseNumber;
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
}
