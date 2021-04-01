package com.postman.step_definitions;

import com.postman.pojos.request.environments.Environment;
import com.postman.pojos.request.environments.NewEnvironment;
import com.postman.pojos.request.environments.Value;
import com.postman.utilities.GlobalDataUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static com.postman.utilities.APIUtils.*;
import static com.postman.utilities.DataUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateEnvironmentsStepDefs {

    private GlobalDataUtils globalDataUtils;

    public CreateEnvironmentsStepDefs(StepData stepData) {
        globalDataUtils = stepData.globalDataUtils;
    }

    @Given("request for creating new environment without variables")
    public void request_for_creating_new_environment_without_variables() {
        NewEnvironment newEnvironment = generateEmptyEnvironmentPOJO();
        Response response = postRequest(newEnvironment, "/environments");

        globalDataUtils.setResponse(response);
        globalDataUtils.setEnvironmentName(newEnvironment.getEnvironment().getName());
        globalDataUtils.setUID(response.path("environment.uid"));
    }

    @Then("verify the response payload environment information")
    public void verify_the_response_payload_environment_information() {
        Response response = globalDataUtils.getResponse();

        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(),is("application/json; charset=utf-8"));
        assertThat(response.path("environment.name"),is(globalDataUtils.getEnvironmentName()));
        deleteRequest("/environments/" + response.path("environment.uid"));
    }

    @Given("request for creating new environment with variables")
    public void requestForCreatingNewEnvironmentWithVariables() {
        NewEnvironment newEnvironment = generateEnvironmentWithVariablePOJO();
        Response response = postRequest(newEnvironment, "/environments");

        globalDataUtils.setResponse(response);
        globalDataUtils.setEnvironmentName(newEnvironment.getEnvironment().getName());
        globalDataUtils.setValue(newEnvironment.getEnvironment().getValues().get(0));
        globalDataUtils.setUID(response.path("environment.uid"));
    }

    @Given("request for creating new environment without environment name")
    public void requestForCreatingNewEnvironmentWithoutEnvironmentName() {
        NewEnvironment newEnvironment = new NewEnvironment(new Environment(""));
        Response response = postRequest(newEnvironment, "/environments");

        globalDataUtils.setResponse(response);
    }

    @Given("request for creating new environment with duplicated variables")
    public void requestForCreatingNewEnvironmentWithDuplicatedVariables() {
        NewEnvironment newEnvironment = generateEnvironmentWithVariablePOJO();
        Value value = newEnvironment.getEnvironment().getValues().get(0);
        newEnvironment.getEnvironment().getValues().add(value);
        Response response = postRequest(newEnvironment, "/environments");

        globalDataUtils.setResponse(response);
    }

    @Given("request for creating new environment without Json body")
    public void requestForCreatingNewEnvironmentWithoutJsonBody() {
        Response response = postRequest("{}", "/environments");

        globalDataUtils.setResponse(response);
    }
}
