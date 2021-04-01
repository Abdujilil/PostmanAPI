package com.postman.step_definitions;

import com.postman.utilities.GlobalDataUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.postman.utilities.APIUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteCollectionsStepDefs {

    private GlobalDataUtils globalDataUtils;

    public DeleteCollectionsStepDefs(StepData stepData) {
        globalDataUtils = stepData.globalDataUtils;
    }

    @When("request for deleting the collection")
    public void requestForDeletingTheCollection() {
        Response response = deleteRequest("/collections/" + globalDataUtils.getUID());

        globalDataUtils.setResponse(response);
    }

    @Then("verify the {string} delete response")
    public void verifyTheDeleteResponse(String object) {
        Response response = globalDataUtils.getResponse();

        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(),is("application/json; charset=utf-8"));
        assertThat(response.path(object + ".uid"),is(globalDataUtils.getUID()));
    }

}
