package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderClass {

	@Test
	public void getHeader() {
		
		when()
			.get("https://www.google.com/")
		
			.then()
				.log().all()
				.header("Content-Type", "text/html; charset=ISO-8859-1")
				.and()
				.header("Content-Encoding", "gzip");
		
	}
	
	@Test
	public void getHeaders() {
		
		Response res =when()
			.get("https://www.google.com/");
		
		String content_header = res.getHeader("Content-Type");  // to get the specific header information
		//System.out.println(content_header);
		
		Headers myHeaders = res.getHeaders();
		
		for(Header header: myHeaders) {
			
			System.out.println("Header name: " +header.getName());
			System.out.println("Header value: " +header.getValue());
			
		}
	}
}
