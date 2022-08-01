package configDemo;

import io.restassured.RestAssured;
import mappingDemo.AnotherUser;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static config.ConfigFactory.getDefaultConfig;
import static org.testng.AssertJUnit.assertEquals;

public class ConfigDemo2 {
    @BeforeSuite
    void setup(){
        RestAssured.config=getDefaultConfig();
    }
    @Test
    public void cleanTestWithHiddenConfig(){
        AnotherUser user=RestAssured.get("https://api.github.com/users/rest-assured")
                .as(AnotherUser.class);

        assertEquals(user.login,"rest-assured");
        assertEquals(user.id,19369327);
    }
}
