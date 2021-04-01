package com.postman.step_definitions;

import com.postman.pojos.request.environments.Environment;
import com.postman.pojos.request.environments.NewEnvironment;
import com.postman.pojos.request.environments.Value;
import com.postman.pojos.response.environments.AllEnvironments;
import com.postman.utilities.GlobalDataUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.postman.utilities.APIUtils.*;
import static com.postman.utilities.DataUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetEnvironmentsStepDefs {

    private GlobalDataUtils globalDataUtils;

    public GetEnvironmentsStepDefs(StepData stepData) {
        globalDataUtils = stepData.globalDataUtils;
    }

    @When("request for getting the environment")
    public void request_for_getting_the_environment() {
        Response response = getRequest("/environments/" + globalDataUtils.getUID());
        globalDataUtils.setResponse(response);
    }
    @Then("verify the get response payload environment information")
    public void verify_the_get_response_payload_environment_information() {
        Response response = globalDataUtils.getResponse();
        Environment environment = response.body().as(NewEnvironment.class).getEnvironment();

        assertThat(response.statusCode(), is(200));
        assertThat(environment.getName(), is(globalDataUtils.getEnvironmentName()));
        assertThat(environment.getId(), is(notNullValue()));
        deleteRequest("/environments/" + globalDataUtils.getUID());
    }

    @Then("verify the get response payload variable information")
    public void verifyTheGetResponsePayloadVariableInformation() {
        Response response = globalDataUtils.getResponse();
        Value value = response.body().as(NewEnvironment.class).getEnvironment().getValues().get(0);

        assertThat(value.getKey(),is(globalDataUtils.getValue().getKey()));
        assertThat(value.getValue(),is(globalDataUtils.getValue().getValue()));
        assertThat(value.getType(), is("text"));
        assertThat(value.isEnabled(), is(true));
    }

    @Given("request for creating multiple new environment without variables")
    public void requestForCreatingMultipleNewEnvironmentWithoutVariables() {
        NewEnvironment newEnvironment1 = generateEmptyEnvironmentPOJO();
        NewEnvironment newEnvironment2 = generateEmptyEnvironmentPOJO();
        Response response1 = postRequest(newEnvironment1, "/environments");
        Response response2 = postRequest(newEnvironment2, "/environments");
        globalDataUtils.setEnvironments(Arrays.asList(newEnvironment1,newEnvironment2));
        globalDataUtils.setUIDs(Arrays.asList(response1.path("environment.uid"),response2.path("environment.uid")));
    }

    @When("request for getting all environments")
    public void requestForGettingAllEnvironments() {
        Response response = getRequest("environments");
        globalDataUtils.setResponse(response);
    }

    @Then("verify the get response payload all environment information")
    public void verifyTheGetResponsePayloadAllEnvironmentInformation() {
        Response response = globalDataUtils.getResponse();
        List<NewEnvironment> expectedEnvironments = globalDataUtils.getEnvironments();
        AllEnvironments allEnvironments = response.body().as(AllEnvironments.class);

        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(), is("application/json; charset=utf-8"));
        assertThat(allEnvironments.getEnvironments().get(0).getCreatedAt(),is(LocalDate.now()));
        assertThat(allEnvironments.getEnvironments().get(1).getCreatedAt(),is(LocalDate.now()));
        assertThat(allEnvironments.getEnvironments().size(),equalTo(expectedEnvironments.size()));
        deleteRequest("/environments/" + globalDataUtils.getUIDs().get(0));
        deleteRequest("/environments/" + globalDataUtils.getUIDs().get(1));
    }

    @When("request for getting the environment with invalid information")
    public void requestForGettingTheEnvironmentWithInvalidInformation() {
        Response response = getRequest("/environments/" + "123");

        globalDataUtils.setResponse(response);
    }

    @Then("verify the environment not found")
    public void verifyTheEnvironmentNotFound() {
        Response response = globalDataUtils.getResponse();

        assertThat(response.statusCode(), is(404));
        assertThat(response.path("error.name"), is("instanceNotFoundError"));
    }
}
