package testCases;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.W2AAPISteps;

import java.io.IOException;

@RunWith(SerenityRunner.class)
public class passJsonAsPayLoad extends BaseTest {

    @Steps
    W2AAPISteps api;

    @Title("Creating multiple users")
    @Test
    public void jsonPayload()
    {
        try {
            api.readJSONfile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
