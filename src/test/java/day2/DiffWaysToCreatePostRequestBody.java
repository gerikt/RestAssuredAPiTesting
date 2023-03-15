package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DiffWaysToCreatePostRequestBody {

	//Post request body using hashmap
	
	@Test(priority=1)
	void testPostusingHashMap()
	{
		HashMap data = new HashMap();
		
		data.put("name", "Scoty");
		data.put("location","France");
		data.put("phone", "123456");
		
		String courseArr[]= {"C","C++"};
		
		data.put("courses", courseArr);
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("Scoty"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		.header("Content-Type", "application/json; charset=utf-8" )
		.log().all();
	}
	
	//deleting student record
	
	@Test(priority=2)
	void testDelete()
	{
		given()
		
		.when()
		.delete("http://localhost:3000/students/4")
		
		.then()
		.statusCode(200);
	}
	
	
	@Test(priority=3)
	void testPostusingJsonLibrary()
	{
	
		JSONObject data = new JSONObject();
		
		data.put("name","Scoty");
		data.put("location","France");
		data.put("phone","123456");
		
		String coursesArr[] = {"C","C++"};
		data.put("courses", coursesArr);
		 

		
		given()
		.contentType("application/json")
		
		//When i am using org.json i have to use data.string 
		.body(data.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("Scoty"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		.header("Content-Type", "application/json; charset=utf-8" )
		.log().all();
	}
	
	// Post request body using POJO class
	
	@Test(priority=3)
	void testPostusingPojoClass()
	{
	
		Pojo_postRequest data = new Pojo_postRequest();
		
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("123456");
		
		String coursesArr[] = {"C","C++"};
		data.setCourses(coursesArr);
		
		
		
		given()
		.contentType("application/json")
		
		//When i am using org.json i have to use data.string 
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		.header("Content-Type", "application/json; charset=utf-8" )
		.log().all();
	}
	
	
	@Test(priority=3)
	void testPostusingExternalJSonFile() throws FileNotFoundException
	{
	
		File f = new File(".\\body.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		
		
		
		given()
		.contentType("application/json")
		
		//When i am using org.json i have to use data.string 
		.body(data.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		.header("Content-Type", "application/json; charset=utf-8" )
		.log().all();
	}
	
	
	
}
