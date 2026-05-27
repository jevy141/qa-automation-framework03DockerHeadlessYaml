package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pages.LoginPage;
import pages.ProductsPage;

import static io.restassured.RestAssured.given;
//using api UI test 
public class ApiUiIntegrationTest extends BaseTest {

    @Test
    public void apiUiIntegrationFlow() {

        String requestBody = "{\n" +
                "  \"title\": \"New Post 2\",\n" +
                "  \"body\": \"Testing my first API UI integration.\",\n" +
                "  \"userId\": 1\n" +
                "}";

        Response response =
                given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                .when()
                    .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                    .extract()
                    .response();

        Assert.assertEquals(response.getStatusCode(), 201);

        String apiTitle = response.jsonPath().getString("title");

        System.out.println("API Created Title: " + apiTitle);

        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.login("standard_user", "secret_sauce");

        ProductsPage productspage = new ProductsPage(getDriver());

        Assert.assertTrue(
                productspage.isProductsPageDisplayed(),
                "Products page not displayed"
        );

        Assert.assertEquals(apiTitle, "New Post 2");
    }
}