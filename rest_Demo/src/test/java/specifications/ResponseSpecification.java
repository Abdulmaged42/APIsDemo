package specifications;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResponseSpecification {
    public static final String BASE_URL="https://api.github.com/";

    @BeforeClass
    public void setup(){
        RestAssured.responseSpecification=new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @AfterClass
    public void cleanup(){
        RestAssured.responseSpecification=null;
    }
    @Test
    public void testWithSpecOne(){
        RestAssured.get(BASE_URL+"non/existing/url")
                .then()
                .statusCode(402)
                .contentType(ContentType.JSON);
    }
    @Test
    public void testWithSpecTwo(){
        RestAssured.get(BASE_URL+"non/existing/url")
                .then()
                .statusCode(HttpStatus.SC_PAYMENT_REQUIRED)
                .contentType(ContentType.JSON);
    }
}
