package configDemo;

import io.restassured.RestAssured;
import io.restassured.listener.ResponseValidationFailureListener;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static config.ConfigFactory.getDefaultConfig;
import static io.restassured.config.FailureConfig.failureConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static org.hamcrest.Matchers.equalTo;

public class ConfigDemo {
    public static final String BASE_URL = "https://api.github.com/";


    @BeforeSuite
    void setup(){
        RestAssured.config=getDefaultConfig();
    }

    @Test
    public void maxRedirect() {
        RestAssured.config = RestAssured.config
                .redirect(redirectConfig().followRedirects(true).maxRedirects(0));
        RestAssured.get(BASE_URL + "repos/twitter/bootstrap")
                .then()
                .statusCode(equalTo(200));
    }

    @Test
    public void failureConfigExample() {
        ResponseValidationFailureListener failureListener = (reqSpec, resSpec, response) ->
                System.out.printf("We have a failure, " +
                                "response status was %s and the body contained: %s",
                        response.getStatusCode(), response.body().asPrettyString());
        RestAssured.config = RestAssured.config()
                .failureConfig(failureConfig().failureListeners(failureListener));

        RestAssured.get(BASE_URL + "users/andrejs-ps")
                .then()
                .body("some.path", equalTo("a thing"));



    }
}
