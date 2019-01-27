package api;

import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ConnectionConfig.connectionConfig;

public class Download {

    public byte[] downloadPDF(String sessionToken,int id,int topicId,int userId) {

        System.out.println("Worksheet ID: "+id);

        byte[] pdf = given()

                .config(RestAssured
                        .config()
                        .connectionConfig(new ConnectionConfig().
                                closeIdleConnectionsAfterEachResponse())
                        .httpClient(HttpClientConfig.httpClientConfig().reuseHttpClientInstance()))
                .contentType("application/pdf")
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
