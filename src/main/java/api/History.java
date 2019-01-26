package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import response.HistoryResponse;

import static io.restassured.RestAssured.given;

public class History {

    public HistoryResponse getHistory(String sessionToken, int userid, int num) {

        Response response = given()
                .contentType(ContentType.JSON)
                .header("session_token",sessionToken)
                .header("user_id",userid)
                .param("page",num)
                .param("topic_id","3,4").when()
                .get("https://www.logiqids.com/api/content/worksheet/history");

        return response.as(HistoryResponse.class);
    }
}
