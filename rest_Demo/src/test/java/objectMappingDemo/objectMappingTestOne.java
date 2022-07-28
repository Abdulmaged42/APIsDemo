package objectMappingDemo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import mappingDemo.AnotherUser;
import mappingDemo.User;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

import static org.testng.Assert.assertEquals;

public class objectMappingTestOne {
    public static final String BASE_URL = "https://api.github.com/users/rest-assured";

    @Test
    public void objectMappingTest() {
        User user = RestAssured.get(BASE_URL)
                .as(User.class);
        assertEquals(user.getLogin(), "rest-assured");
        assertEquals(user.getId(), 19369327);
        assertEquals(user.getPublicRepos(), 2);
    }

    @Test
    public void objectMappingUsingSpecifiedMapper() {
        AnotherUser user = RestAssured.get(BASE_URL)
                .as(AnotherUser.class, getMapper());
        assertEquals(user.login, "rest-assured");

    }

    private Jackson2Mapper getMapper() {
        return new Jackson2Mapper(new Jackson2ObjectMapperFactory() {
            @Override
            public ObjectMapper create(Type type, String s) {
                ObjectMapper om = new ObjectMapper();
                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return om;
            }
        });
    }
    private Jackson2Mapper getMapperLambda(){
        return new Jackson2Mapper(((type, s) -> {
            ObjectMapper om =new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            return om;
        }));
    }

}
