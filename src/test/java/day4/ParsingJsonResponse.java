package day4;



import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;



public class ParsingJsonResponse {

	
	@Test(priority=1)
	void testJsonResponse()
	{
	/*	
		
		//Aproach 1 
		
		given()
			.contentType("ContentType.JSON")
		.when()
			.get("http://localhost:3000/students")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("[0].name", equalTo("John"))
			.log().all();
			
		*/	
		
		
		//Approach 2 
		
	    Response res =	given()
	    		.contentType("ContentType.JSON")
		
		.when()
			.get("http://localhost:3000/students");
	    
	   Assert.assertEquals(res.getStatusCode(), 200);  //validation 1
	   Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8" );  
	    
	   String name =  res.jsonPath().get("[0].name").toString();
	   Assert.assertEquals(name, "John");
	    
	    //JSONObject class
	    
	}
	@Test(priority=2)
	void testJsonResponseBodyData()
	{
		Response res =
		
		given()
			.contentType(ContentType.JSON)
		.when()
		.get("http://localhost:3000/students");
		
		//For this to work i had to modify the code from JSONObject to Json array
		//because the response that i am getting on postman starts with a [ instead of {
		
		
		/*
		 * JSONObject jo = new JSONObject(res.asString()); 
		 * 
		 * for(int i =0; i<jo.getJSONArray("students").length();i++)
		{
			String location = jo.getJSONArray("students").getJSONObject(i).get("location").toString();
			System.out.println(location);
		}
		 * 
		 */
		
		
		
		
		JSONArray ja = new JSONArray(res.asString());   // converting response to json object
		
		boolean status=false;
		
		for(int i =0; i<ja.length();i++)
		{
			String location = ja.getJSONObject(i).get("location").toString();
			System.out.println(location);
			
			if(location.equals("Greece"))
			{
				status=true;
				break;
			}
			
		}
		
		Assert.assertEquals(status, true);
		
		
	}
	
}
