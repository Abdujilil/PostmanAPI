package com.postman.step_definitions;

import com.postman.pojos.request.collections.Collection;
import com.postman.pojos.request.collections.NewCollection;
import com.postman.pojos.response.collections.singleCollection.Item;
import com.postman.pojos.response.collections.singleCollection.SingleCollection;

import com.postman.utilities.GlobalDataUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static com.postman.utilities.APIUtils.*;
import static com.postman.utilities.DataUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateCollectionsStepDefs {

    private GlobalDataUtils globalDataUtils;

    public CreateCollectionsStepDefs(StepData stepData) {
        globalDataUtils = stepData.globalDataUtils;
    }

    @Given("request for creating new collection without requests inside")
    public void request_for_creating_new_collection_without_requests_inside() {
        NewCollection newCollection = generateCollectionOnlyPOJO();
        Response response = postRequest(newCollection,"/collections");

        globalDataUtils.setCollectionName(newCollection.getCollection().getInfo().getName());
        globalDataUtils.setResponse(response);
        globalDataUtils.setUID(response.path("collection.uid"));
    }

    @Given("request for creating new collection with {string} request inside")
    public void requestForCreatingNewCollectionWithRequestInside(String method) {
        NewCollection newCollection = generateCollectionPOJOWithReq(method);
        Response response = postRequest(newCollection, "/collections");

        globalDataUtils.setCollectionName(newCollection.getCollection().getInfo().getName());
        globalDataUtils.setItem(newCollection.getCollection().getItem().get(0));
        globalDataUtils.setResponse(response);
        globalDataUtils.setUID(response.path("collection.uid"));
    }

    @Then("verify the response payload collection information")
    public void verify_The_Response_Payload_Collection_Information() {
        Response response = globalDataUtils.getResponse();

        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(), is("application/json; charset=utf-8"));
        assertThat(response.path("collection.name"), is(globalDataUtils.getCollectionName()));
        deleteRequest("/collections/" + response.path("collection.uid"));
    }

    @Then("verify the response payload request information")
    public void verify_the_response_payload_request_information() {
        Response getResponse = getRequest("/collections/" + globalDataUtils.getUID());
        Item item = getResponse.body().as(SingleCollection.class).getCollection().getItem().get(0);

        assertThat(item.getName(),is(globalDataUtils.getItem().getName()));
        assertThat(item.getRequest().getMethod(),is(globalDataUtils.getItem().getRequest().getMethod()));
        assertThat(item.getRequest().getUrl().getRaw(),is(globalDataUtils.getItem().getRequest().getUrl()));
    }

    @Given("request for creating new collection with invalid information")
    public void requestForCreatingNewCollectionWithInvalidInformation() {
        Collection collection = new Collection();
        NewCollection newCollection = new NewCollection(collection);
        Response response = postRequest(newCollection,"/collections");
        globalDataUtils.setResponse(response);
    }

    @Then("verify the {string} response information")
    public void verifyTheResponseInformation(String error) {
        Response response = globalDataUtils.getResponse();

        assertThat(response.statusCode(),is(400));
        assertThat(response.path("error.name"),is( error.replaceAll(" ","") ));
    }

}