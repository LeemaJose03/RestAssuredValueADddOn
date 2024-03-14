package day1;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class HttpRequest {

	int id;
	
	@Ignore
	@Test
	public void getUsers() {  //To get the user list. 
		
		given()    // pre-requisites
		
		.when()
			.get("https://reqres.in/api/user?page=2")   // condition
			
		
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all(); // validation
		
	}
	
	@Test(priority = 1)
	public void putUser() {
		
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "morpheus");
		data.put("job", "zion resident");
		
		id = given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");  // getting the int id from jason path
		
		/*.then()
			.statusCode(201)  // commenting out to get the id from the json path for updation
			.body("name", equalTo("morpheus")); */
	}
	
	@Test(priority=2, dependsOnMethods = {"putUser"})
	public void updateUser() {
		
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "morpheus");
		data.put("job", "resident");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
			.put("https://reqres.in/api/users/" +id)
			
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority = 3)
	public void deleteUser() {
		
		when()
			.delete("https://reqres.in/api/users/" +id)
		
		.then()
			.statusCode(204)
			.log().all();
	}
}
