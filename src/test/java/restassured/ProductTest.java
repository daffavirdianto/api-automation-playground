package restassured;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.model.ProductResponse;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductTest {

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "https://automationexercise.com/api";
    }

    @Test
    public void testGetAllProductsList() {
        Response response = RestAssured.given()
                .log().all()
                .when()
                .get("/productsList");

        System.out.println(response.asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.jsonPath().getList("products").size() > 0, "Product list should not be empty");
    }

    @Test
    public void testSearchProduct() throws Exception{
        Response response = RestAssured.given()
                .formParam("search_product", "Blue Top")
                .log().all()
                .when()
                .post("/searchProduct");

        String responseBody = response.asString();
        ProductResponse productResponse = parseResponse(responseBody);

        Assert.assertEquals(productResponse.getResponseCode(), 200, "Response code is not as expected");
        Assert.assertEquals(productResponse.getProducts().get(0).getName(), "Blue Top",
                "Product name is not as expected");
        Assert.assertEquals(productResponse.getProducts().get(0).getPrice(), "Rs. 500",
                "Product price is not as expected");
        Assert.assertEquals(productResponse.getProducts().get(0).getBrand(), "Polo",
                "Product brand is not as expected");
        Assert.assertEquals(productResponse.getProducts().get(0).getCategory().getUsertype().getUsertype(), "Women",
                "User type is not as expected");
        Assert.assertEquals(productResponse.getProducts().get(0).getCategory().getCategory(), "Tops",
                "Product category is not as expected");
    }

    private ProductResponse parseResponse(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, ProductResponse.class);
    }
}
