package restassured;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterUserTest {

    private static final Logger logger = LoggerFactory.getLogger(RegisterUserTest.class);

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://automationexercise.com/api";
    }

    @Test(priority = 1)
    public void testRegisterUser() {

        logger.info("add RegisterUser test case running...");

        Response response = RestAssured.given()
                .multiPart("name", "Daffa Virdianto")
                .multiPart("email", "daffa.virdianto1@gmail.com")
                .multiPart("password", "securepassword123")
                .multiPart("firstname", "Daffa")
                .multiPart("lastname", "Virdianto")
                .multiPart("company", "Playground")
                .multiPart("address1", "Jl. Raya No. 1")
                .multiPart("country", "Indonesia")
                .multiPart("zipcode", "12345")
                .multiPart("state", "Indonesia")
                .multiPart("city", "Semarang")
                .multiPart("mobile_number", "+1234567890")
                .when()
                .post("/createAccount");

        logger.debug(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("message"), "User created!");
    }

    @Test(priority = 2, dependsOnMethods = "testRegisterUser")
    public void testRegisterUser_EmailAlreadyExists() {
        Response response = RestAssured.given()
                .multiPart("name", "Daffa Virdianto")
                .multiPart("email", "daffa.virdianto1@gmail.com")
                .multiPart("password", "securepassword123")
                .multiPart("firstname", "Daffa")
                .multiPart("lastname", "Virdianto")
                .multiPart("company", "Playground")
                .multiPart("address1", "Jl. Raya No. 1")
                .multiPart("country", "Indonesia")
                .multiPart("zipcode", "12345")
                .multiPart("state", "Indonesia")
                .multiPart("city", "Semarang")
                .multiPart("mobile_number", "+1234567890")
                .when()
                .post("/createAccount");

        logger.debug(response.asString());
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 400);
        Assert.assertEquals(response.jsonPath().getString("message"), "Email already exists!");
    }

    @Test(dependsOnMethods = "testRegisterUser_EmailAlreadyExists")
    public void testDeleteUser() {

        RestAssured.given()
                .multiPart("email", "daffa.virdianto1@gmail.com")
                .multiPart("password", "securepassword123")
                .log().all()
                .when()
                .delete("/deleteAccount");
    }

    @Test
    public void testRegisterUser_MissingRequiredFields() {

        Response response = RestAssured.given()
                .when()
                .post("/createAccount");

        logger.debug(response.asString());

        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 400);
    }

}
