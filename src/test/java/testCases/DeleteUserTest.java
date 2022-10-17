package testCases;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.W2AAPISteps;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

@RunWith(SerenityRunner.class)
public class DeleteUserTest extends BaseTest {
	
	
	@Steps
	W2AAPISteps api;
	
	
	@Title("Deleting the user details")
	@Test
	public void deleteUserTest() {

		api.sendDeleteRequestForUser("23");
		api.validateStatusCodeToBe(200);
	}

}
