package body;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import java.util.Map;

public class BasicBodyDemo {
    public  static final String BASE_URL="https://api.github.com/rate_limit";

    @Test
    public void jsonPathReturnMap(){
        Response response= RestAssured.get(BASE_URL).prettyPeek();

        ResponseBody<?> body=response.body();
        JsonPath jPath=body.jsonPath();

        JsonPath jPath2=response.body().jsonPath();

        Map<String,String> fullJson=jPath2.get();
        Map<String,String> subMap=jPath2.get("resources");
        Map<String,String> subMap2=jPath2.get("resources.core");

        int value =jPath.get("resources.core.limit");
        int value2 =jPath.get("resources.graphql.remaining");

        System.out.println("fullJson :"+fullJson);
        System.out.println("subMap :"+subMap);
        System.out.println("subMap2 :"+subMap2);
        System.out.println("value :"+value);
        System.out.println("value2 :"+value2);

    }

}
