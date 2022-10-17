package rough;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;


import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;


@RunWith(SerenityRunner.class)
public class FirstTest {
    String id;
    String response1;
    Response  response;
    @BeforeClass
    public static void init(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="/api/users/";

    }

    @Title("Sending http requests test")
    @Test()
    public void testRequest() {


        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "Anshu");
        map.put("job", "teacher");
        JSONObject request=new JSONObject(map);
       //System.out.println(request);
       //System.out.println(request.toJSONString());
        response1= SerenityRest.given().header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                 .body(request.toJSONString()).
                  when().
                  post().then().extract().response().asString();
        System.out.println("response is "+response1);
        JsonPath js=new JsonPath(response1);
         id=js.getString("id");
        System.out.println(id);


       //get
        response = SerenityRest.given().when().get(id);
        response.prettyPrint();
        System.out.println(response.getStatusCode());
        response.then().statusCode(404);

        //update
        JSONObject req= new JSONObject();
        req.put("name", "Ram");
        req.put("job", "Teacher");

        response1= SerenityRest.given().header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(req.toJSONString()).
                when().
                put().then().extract().response().asString();

        System.out.println("response is "+req);



        //delete
        response = SerenityRest.given().when().delete(id);
        System.out.println(id);
        response.prettyPrint();
        System.out.println(response.getStatusCode());
        response.then().statusCode(204);


    }


}
