package restassured;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserDetailTest {

    @BeforeTest
    public void setUp() {
        io.restassured.RestAssured.baseURI = "https://automationexercise.com/api";
    }

    @Test
    public void testGetUserDetails() {
        Response response = RestAssured.given()
                .param("email", "daffa@example.com")
                .log().all()
                .when()
                .get("/getUserDetailByEmail");

        System.out.println(response.asString());

        try {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/user-detail-schema.json"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}
