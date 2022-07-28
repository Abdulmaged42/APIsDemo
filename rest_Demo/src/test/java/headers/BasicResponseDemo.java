package headers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BasicResponseDemo {
    public  static final String BASE_URL="https://api.github.com";
    @Test
    public void convenienceMethods(){
        Response response= RestAssured.get(BASE_URL);
        assertEquals(response.getStatusCode(),200);
        assertEquals(response.getContentType(),"application/json; charset=utf-8");
    }
    @Test
    public void genericHeader(){
        Response response= RestAssured.get(BASE_URL);
        assertEquals(response.getHeader("server"),"GitHub.com");
        /**
         * x-ratelimit-limit : is a custom header telling us how many free calls you can make
         * within a certain timeframe without authentication
         */
        assertEquals(response.getHeader("x-ratelimit-limit"),"60");
        assertEquals(Integer.parseInt(response.getHeader("x-ratelimit-limit")),60);
    }

}
