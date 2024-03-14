package day1;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;



public class PostRequest {

	
	/* 4 ways we can create request body
	 * 1) using HashMap
	 * 2) using org.json
	 * 3) using POJO class ( Plain Java Old Object)
	 * 4) using external json file
	 */
	
	@Test
	public void postJson() {
		
		JSONObject data = new JSONObject();
		data.put("name", "Leema");
		data.put("role", "Mentor");
		
		given()
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Leema"))
			.log().all();
	}
	
	@Test
	public void postPojo() {
		
		PojoClass data = new PojoClass();
		data.setName("Jose");
		data.setRole("Clerk");
		
		given()
		.contentType("application/json")
		.body(data)
	
	.when()
		.post("https://reqres.in/api/users")
		
	.then()
		.statusCode(201)
		.body("name", equalTo("Jose"))
		.log().all();
		
		
	}
	
	@Test
	public void postJsonFile() throws FileNotFoundException {
		
		File file = new File("D:\\EclipseClass\\RestAssuredValueAddOn\\src\\test\\java\\day1\\data.json");
		
		FileReader fr = new FileReader(file);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(data.toString())
	
	.when()
			.post("https://reqres.in/api/users")
		
	.then()
		.statusCode(201)
		.body("name", equalTo("Dania"))
		.log().all();
		
		
		
		
	}
}
