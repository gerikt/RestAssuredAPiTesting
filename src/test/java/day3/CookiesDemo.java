package day3;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

public class CookiesDemo {

	@Test
	void testCookies()
	{
		given()
		
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.log().all();
	}
	
	public class getCookiesInfo {

		@Test(priority=2)
		void testCookies()
		{
			
			Response res = given()
			
			.when()
			.get("https://www.google.com/");
			
			
			
			
			//Get single cookie information
			
		   //	String cookie_value = res.getCookie("AEC");
			
		   //	System.out.println("Value of cookie is ========>   " + cookie_value);
			
			
			//Get all cookies information
			
			Map<String,String> cookies_values =  res.getCookies();
		   
			System.out.println(cookies_values.keySet()); //keyset prints only the name of the cookie
			
			
			for(String k :cookies_values.keySet())
			{
				String cookie_value = res.getCookie(k);
				System.out.println(k +" " + cookie_value);
			}
		}
	
	
}
}
