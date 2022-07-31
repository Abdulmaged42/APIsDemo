package otherMethods;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.testng.annotations.Test;

public class OtherMethodsDemo {
    public  static final String BASE_URL="https://api.github.com/user/repos";
    public static final String TOKEN ="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";
    @Test(description = "Create me")
    public void postTest(){
        RestAssured
                .given()
                .header("Authorization","token "+TOKEN)
                .body("{\"name\":\"deleteme\"}")
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201);
    }
    @Test(description = "Update a repo")
    public void patchTest(){
        RestAssured
                .given()
                .header("Authorization","token "+TOKEN)
                .body("{\"name\":\"deleteme-patched\"}")
                .when()
                .patch("https://github.com/Abdulmaged42/deleteme")
                .then()
                .statusCode(200);
    }
    @Test(description = "delete a repo")
    public void deleteTest(){
        RestAssured.reset();
        RestAssured.config = RestAssuredConfig.config().httpClient( new HttpClientConfig().setParam(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH));
        RestAssured
                .given()
                .header("Authorization","token "+TOKEN)
                .when()
                .delete("https://github.com/Abdulmaged42/deleteme")
                .prettyPeek()
                .then()
                .statusCode(204);
    }
}
