package body;

import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ValidatableResponseBodyDemo {
    public  static final String BASE_URL="https://api.github.com/";

    @Test
    public void matcherExample(){
        RestAssured.get(BASE_URL)
                .prettyPeek()
                .then()
                .body("current_user_url",equalTo(BASE_URL+"user"))
                .body(containsString("feeds_url"))
                .body(containsString("feeds_url"),containsString("current_user_url"));
    }

    @Test
    public void complexBodyExample(){
        RestAssured.get(BASE_URL+"users/andrejs-ps")
                .prettyPeek()
                .then()
                .body("url",response -> containsString("andrejs-ps"))
                .body("url",response -> containsString(response.body().jsonPath().get("login")));
    }
    @DataProvider
    public Object[][] names(){
        return new Object[][]{
                {"andrejs-ps"},
                {"rest-assured"}
        };
    }
    @Test(dataProvider = "names")
    public void complexBodyExampleWithDp(String name){
        RestAssured.get(BASE_URL+"users/"+name)
                .prettyPeek()
                .then()
                .body("url",response -> containsString(response.body().jsonPath().get("login")));
    }
}
