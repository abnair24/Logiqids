package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import response.LoginResponse;
import util.PropertiesReader;

import static io.restassured.RestAssured.given;

public class Login {

    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public LoginResponse login() {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("email", propertiesReader.getUsername());
        jsonObject.put("password",propertiesReader.getPassword());

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .when()
                .post("https://www.logiqids.com/api/login");
        return response.as(LoginResponse.class);

    }

}
