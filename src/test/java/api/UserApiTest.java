/*package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserApiTest {

	
	@Test
	public void getUsersTest()
	{
		Response response= RestAssured.get("https://jsonplaceholder.typicode.com/todos/1");
		String body =response.getBody().asString();
		int statusCode=response.getStatusCode();
		System.out.println(body);
		System.out.println(statusCode);
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertTrue(response.asString().contains("title"));
		
		System.out.println(response.asPrettyString());
		JsonPath jsonpath=response.jsonPath();
		String jsonTitle=jsonpath.getString("title");
		int jsonId=jsonpath.getInt("id");
		System.out.println(jsonTitle);
		System.out.println(jsonId);
		
	}
}
*/


package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.Reporter;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class UserApiTest {

    @Test
    public void getUsersTest() {

        RestAssured.baseURI = "https://reqres.in";

        Response response =
                RestAssured
                        .given().header("x-api-key","free_user_3DLaeRWhD1Patqt5GY25U5vfX7p")
                        .when()
                        .get("/api/users?page=2");

        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.asString().contains("data"));
        
        
        Assert.assertEquals(response.jsonPath().getInt("page"),2);
        Assert.assertTrue(response.jsonPath().getList("data").size() >0);
    }
    @Test
    public void postUsersTest()
    {
    	String requestbody="{\r\n"
		 		+ "  \"title\": \"New Post 2\",\r\n" 
		 		+ "  \"body\": \"Testing my first API call.\",\r\n"
		 		+ "  \"userId\": 1\r\n"
		 		+ "}";
    	Response response= given().contentType(ContentType.JSON).
    			          body(requestbody)
    			          .when().post("https://jsonplaceholder.typicode.com/posts")
    			          .then().extract().response();
    			System.out.println(response.getBody().asString());   
    			System.out.println(response.getStatusCode()); 
    			Assert.assertEquals(response.getStatusCode(),201);
    			
       JsonPath jsonpath=response.jsonPath();
       System.out.println(jsonpath.getString("title"));
       System.out.println(jsonpath.getInt("userId"));
       
    }
}