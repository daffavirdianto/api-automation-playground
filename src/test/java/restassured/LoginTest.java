package restassured;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://automationexercise.com/api";
    }

    @Test
    public void testRegisterUser() {
        Response response = RestAssured.given()
                .multiPart("name", "Daffa Virdianto")
                .multiPart("email", "daffa.virdianto@gmail.com")
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

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("message"), "User created!");
    }

    @Test(dependsOnMethods = "testRegisterUser")
    public void validCredentials() {

        Response response = RestAssured.given()
                .multiPart("email", "daffa.virdianto@gmail.com")
                .multiPart("password", "securepassword123")
                .log().all()
                .when()
                .post("/verifyLogin");

        System.out.println(response.asString());
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 200);
        Assert.assertEquals(response.jsonPath().getString("message"), "User exists!");
    }

    @Test(dependsOnMethods = "testRegisterUser", dataProvider = "invalidCredentialsData")
    public void invalidCredentials(String email, String password, Integer errorCode, String errorMessage) {

        /*
         * 1. Test with email not registered and password
         * 2. Test with email found but password incorrect
         * 3. Test with email and empty password
         */
        Response response = RestAssured.given()
                .multiPart("email", email)
                .multiPart("password", password)
                .log().all()
                .when()
                .post("/verifyLogin");

        System.out.println(response.asString());
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), errorCode);
        Assert.assertEquals(response.jsonPath().getString("message"), errorMessage);
    }

    @DataProvider(name = "invalidCredentialsData")
    public Object[][] invalidCredentialsData() {
        return new Object[][] {
            {"daffa.virdianto1@gmail.com","securepassword123", 404, "User not found!"},
            {"daffa.virdianto@gmail.com","securepassword", 404, "User not found!"},
            {"daffa.virdianto@gmail.com", "", 404, "User not found!"},
        };
    }

    @AfterClass
    public void tearDown() {
        RestAssured.given()
                .multiPart("email", "daffa.virdianto@gmail.com")
                .multiPart("password", "securepassword123")
                .log().all()
                .when()
                .delete("/deleteAccount");
    }
}