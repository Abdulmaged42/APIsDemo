package headAndOptions;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class HeadAndOptionsDemo {
    public  static final String BASE_URL="https://api.github.com/";

    @Test
    public void headTest(){
        RestAssured.head(BASE_URL)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body(emptyOrNullString());
    }
    @Test
    public void optionTest(){
        RestAssured.options(BASE_URL)
                .prettyPeek()
                .then()
                .statusCode(204)
                .header("access-control-allow-methods","GET, POST, PATCH, PUT, DELETE")
                .body(emptyOrNullString());
    }
}
