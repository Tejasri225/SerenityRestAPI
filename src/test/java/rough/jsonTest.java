package rough;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.W2AAPISteps;
import testCases.BaseTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SerenityRunner.class)
public class jsonTest {


    @Test
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
