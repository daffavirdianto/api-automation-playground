package stepdefinitions;

import io.restassured.response.Response;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.Map;

import org.testng.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserStepDefinitions  {

    private Response response;

    @When("I send a registration request with:")
    public void sendRegistrationAccountRequest(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);

        response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParams(userData)
                .when()
                .post("https://automationexercise.com/api/createAccount");
    }

    @Then("the user should be created successfully")
    public void createUserSuccesfully() {
        Assert.assertEquals(response.jsonPath().getString("message"), "User created!");
    }

    @When("I send a login request with:")
    public void sendLoginAccountRequest(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);

        response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParams(userData)
                .when()
                .post("https://automationexercise.com/api/verifyLogin");
    }

    @Then("the user should be logged in successfully")
    public void loginSuccesfully() {
        Assert.assertEquals(response.jsonPath().getString("message"), "User exists!");
    }

    @When("I send an update account request with:")
    public void sendUpdatedAccountRequest(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);

        response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParams(userData)
                .when()
                .put("https://automationexercise.com/api/updateAccount");
    }

    @Then("the user account should be updated successfully")
    public void updatedUserSuccesfully() {
        Assert.assertEquals(response.jsonPath().getString("message"), "User updated!");
    }

    @When("I send a get user detail request with email {string}")
    public void sendGetUserDetailRequest(String email) {
        response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("email", email)
                .when()
                .get("https://automationexercise.com/api/getUserDetailByEmail");
    }

    @Then("the response should match the {string} JSON schema")
    public void responseShouldMatchJsonSchema(String schemaPath) {
        response.then().assertThat()
            .body(matchesJsonSchemaInClasspath(schemaPath));
    }

    @When("I send a delete account request with:")
    public void sendDeleteAccountRequest(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);

        response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParams(userData)
                .when()
                .delete("https://automationexercise.com/api/deleteAccount");
    }

    @Then("the user account should be deleted successfully")
    public void deletedUserSuccessfully() {
        Assert.assertEquals(response.jsonPath().getString("message"), "Account deleted!");
    }
}
