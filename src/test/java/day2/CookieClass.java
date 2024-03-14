package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookieClass {

	@Test
	public void getCookie() {
		
		Response res = 
				
		when()
			.get("https://www.google.com/");
		
		String cookie_value = res.getCookie("AEC");  // returns a string --> to extract specific cookie information
		System.out.println(cookie_value);
		
		Map<String, String> cookies = res.getCookies(); // to extract list of cookies and it's value. 
		for(String cookie: cookies.keySet()) {
			
			System.out.println(cookie+" "+res.getCookie(cookie));
		}
		
		
		
//		.then()
//			.log().cookies()
//			.cookie("AEC", "Ae3NU9M1mC8oEQTB5BdkRAz1GWAHw7e80A1F-qoRk18RCQs0Kvf8P5WXFXw");
//		
	}
	
}
