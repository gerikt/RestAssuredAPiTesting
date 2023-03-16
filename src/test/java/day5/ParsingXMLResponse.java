package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class ParsingXMLResponse {

	@Test(priority=1)
	void testXmlResponse()
	{
		//Approach 1
	/*	
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type","application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page",equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation.name[0]", equalTo("Developer"));
	*/
		
		//Approach 2 
		Response res =
		
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
	
		String pageNo =res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
		
		String travelName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name");
		Assert.assertEquals(travelName, "Developer");
		
		
	}
	
	@Test
	void testXMLResponseBody()
	{
		Response res =
				
				given()
				
				.when()
					.get("http://restapi.adequateshop.com/api/Traveler?page=1");
				
			XmlPath  xmlobj =new XmlPath(res.asString()); 
			
			//getList method will get all the  nods 
			List<String> travellers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
			System.out.println(travellers.size());
			Assert.assertEquals(travellers.size(), 10);
			
			//verify travveler name is present in response
			
			List<String> travellerNames = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
			
			boolean status=false;
			
			for(String tn : travellerNames)
			{
				System.out.println(tn);
				
				if(tn.equals("karen"))
				{
					status=true;
					break;
				}
			}
			
			Assert.assertEquals(status, true);
			
			
	}
}
