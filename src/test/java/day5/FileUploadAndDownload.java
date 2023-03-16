package day5;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
public class FileUploadAndDownload {

	@Test
	void singleFileUpload()
	{			File myfile = new File("C:\\Users\\gerik\\OneDrive\\Desktop\\json files\\Book1.csv");

		given()
			.multiPart("file",myfile)
			.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
			.body("fileName",equalTo("Book1.csv"))
			.log().all();
			
	}
}
