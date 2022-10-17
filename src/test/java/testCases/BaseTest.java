package testCases;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BaseTest {

	@BeforeClass
	public static void init() {

		RestAssured.baseURI="http://localhost:8080";
		RestAssured.basePath="/api/users/";
	}
	
}
