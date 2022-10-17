package sampleAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

import static net.serenitybdd.rest.SerenityRest.given;

public class BaseTest1 {

    public static void main(String args[])  {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String addresponse=	given().log().all().queryParam("Key", "qaclick123")
                .body(PayLoad.addData())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .extract().response().asString();
        System.out.println("\n\n\naddresponse: \n"+addresponse);

        JsonPath js=new JsonPath(addresponse);
        String placeid=js.getString("place_id");
        System.out.println(placeid);

        //update
        given().log().all().queryParam("Key", "qaclick123")
                .header("Content-Type","application/json")
                .body("{\r\n"
                        +"\"place_id\":\""+placeid+"\",\r\n"
                        + "\"address\":\"xyz\",\r\n"
                        + "\"key\":\"qaclick123\"\r\n"
                        + "}")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
//get
        given().log().all().queryParam("place_id",placeid).queryParam("Key", "qaclick123")
                .when().get("maps/api/place/get/json")
                .then().log().all().statusCode(200).extract().response().asString();
    }
}
