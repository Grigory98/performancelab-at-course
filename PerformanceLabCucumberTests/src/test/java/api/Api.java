package api;

import static io.restassured.RestAssured.given;
import api.schemes.Users;
import core.DriversManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.util.Arrays;
import java.util.Comparator;

public class Api {

    public static String getToken() {
        RestAssured.baseURI = DriversManager.config().apiUrl;
        RestAssured.port = DriversManager.config().apiPort;
        RestAssured.defaultParser = Parser.JSON;

        var authRequest = new Authorization.AuthRequest(DriversManager.config().username, DriversManager.config().password);
        var authResponse = given().auth().none().and().disableCsrf().and()
                .contentType(ContentType.JSON).body(authRequest)
                .when().post("/login").as(Authorization.AuthResponse.class);

        return "Bearer " + authResponse.accessToken;
    }

    public static Users[] getUsers()
    {
        var response = given().when().get( "/users");
        return response.as(Users[].class);
    }

    public static boolean checkUsersSortAsc()
    {
        var users = getUsers();
        var userMinId = Arrays.stream(users).min(Comparator.comparingInt(x -> x.id)).map(x -> x.id).orElseThrow();
        var userMaxId = Arrays.stream(users).max(Comparator.comparingInt(x -> x.id)).map(x -> x.id).orElseThrow();

        var ascResponse = given().when().get( "/users?sort=id&order=asc");
        var ascUsers = ascResponse.as(Users[].class);
        return ascUsers[0].id == userMinId && ascUsers[ascUsers.length - 1].id == userMaxId;
    }

    public static boolean checkUsersSortDesc()
    {
        var users = getUsers();
        var userMinId = Arrays.stream(users).min(Comparator.comparingInt(x -> x.id)).map(x -> x.id).orElseThrow();
        var userMaxId = Arrays.stream(users).max(Comparator.comparingInt(x -> x.id)).map(x -> x.id).orElseThrow();

        var descResponse = given().when().get( "/users?sort=id&order=desc");
        var descUsers = descResponse.as(Users[].class);
        return descUsers[0].id == userMaxId && descUsers[descUsers.length - 1].id == userMinId;
    }
}
