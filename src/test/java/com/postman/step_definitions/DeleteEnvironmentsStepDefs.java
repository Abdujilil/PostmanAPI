package com.postman.step_definitions;

import com.postman.utilities.GlobalDataUtils;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.postman.utilities.APIUtils.*;

public class DeleteEnvironmentsStepDefs {

    private GlobalDataUtils globalDataUtils;

    public DeleteEnvironmentsStepDefs(StepData stepData) {
        globalDataUtils = stepData.globalDataUtils;
    }

    @When("request for deleting the environment")
    public void request_for_deleting_the_environment() {
        Response response = deleteRequest("/environments/" + globalDataUtils.getUID());

        globalDataUtils.setResponse(response);
    }

}
