package springServer.testEndpoints;
import org.json.JSONObject;
import springServer.businessLogic.Flat;
import springServer.oauth2Config.Roles;
import springServer.users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class GenTestJsons {
    public static void main(String[] args) {
//        testParseUser();
        genFlatJson();
    }

    private static void testParseUser () {
        List<Roles> tempRoles = new ArrayList<>();
        tempRoles.add(Roles.ADMIN);
        User myUser = new User("test", "testemail", "photoUrl", tempRoles);
        JSONObject jsonUser = myUser.toJson();
        System.out.println("Stary " + jsonUser.toString());
        User newUser = User.fromJson(jsonUser);
        System.out.println("nowy " + newUser.toString());
    }

    private static void genFlatJson() {
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
