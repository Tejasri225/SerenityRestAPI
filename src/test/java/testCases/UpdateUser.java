package testCases;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.W2AAPISteps;

@RunWith(SerenityRunner.class)
//@UseTestDataFrom("./src/test/resources/testdata/users.csv")
public class UpdateUser extends BaseTest{

    @Steps
    W2AAPISteps api;


    @Title("Creating Multiple users test")
    @Test
    public void createUserTest() {

        api.sendPUTRequest("Tejasri","Chanamalla");

    }
}
