package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Download {

    public byte[] downloadPDF(String sessionToken,int id,int topicId,int userId) {

        byte[] pdf = given()
                .contentType(ContentType.JSON)
                .header("session_token",sessionToken)
                .header("user_id",userId)
                .pathParam("id",id)
                .pathParam("topicId",topicId)
                .when()
                .post("https://www.logiqids.com/api/content/topic/{topicId}/worksheet/{id}/downloadWorksheet")
                .then()
                .statusCode(200)
                .extract()
                .asByteArray();

        return pdf;
    }
}
