package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import testCases.BaseTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class W2AAPISteps extends BaseTest{
	
	private Response response;
	//private String response1;
	
	@Step("Send get request for user id: {0}")
	public void sendGetRequestForUser(String userId) {
		
		response = SerenityRest.given().when().get(userId);
		response.prettyPrint();
	}

	@Step("Send POST request for user creating with email: {0}, firstname {1} and lastname {2}")
	public void sendPOSTRequest(String email,String firstName, String lastName) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("firstName", firstName);
		map.put("lastName", lastName);

		response = SerenityRest.given()
				.contentType(ContentType.JSON)
				.body(map).log().all().post();
		response.prettyPrint();
	}

	@Step("Send PUT request for user creating with: firstname {1} and lastname {2}")
	public void sendPUTRequest(String firstName, String lastName) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("firstName", firstName);
		map.put("lastName", lastName);

		response = SerenityRest.given()
				.contentType(ContentType.JSON)
				.body(map).log().all().put("24");
		response.prettyPrint();
	}
	
	@Step("Send Delete request for user id: {0}")
	public void sendDeleteRequestForUser(String record) {
		
		response = SerenityRest.given().when().delete(record);
		response.prettyPrint();
	}
	
	@Step("Validate status code to be {0}")
	public void validateStatusCodeToBe(int code) {
		System.out.println(response.getStatusCode());
		response.then().statusCode(code);

	}

	@Step("Verify reponse body for key {0} and value {1}")
	public void verifyResponseBody(String key,String value) {
		
		response.then().body(key, equalTo(value));
		
		
	}
	@Step
	public void readJSONfile() throws IOException {

		//read data from local JSON file then store in byte array
		byte[] b = Files.readAllBytes(Paths.get("src/test/resources/testData/payLoad.json"));

		//convert byte array to string
		String bdy = new String(b);

		//base URL
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		//input details with header and body
		RestAssured.given().header("Content-type", "application/json").body(bdy)

				//adding post method
				.when().post("/posts").then().log().all()
				//verify status code as 201
				.assertThat().statusCode(201);
	}
	
	
}
