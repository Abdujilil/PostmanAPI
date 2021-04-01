package com.postman.step_definitions;

import com.postman.pojos.request.collections.NewCollection;
import com.postman.pojos.response.collections.allCollections.AllCollections;
import com.postman.pojos.response.collections.singleCollection.Item;
import com.postman.pojos.response.collections.singleCollection.SingleCollection;
import com.postman.utilities.ConfigurationReader;
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

public class GetCollectionsStepDefs {

    private GlobalDataUtils globalDataUtils;

    public GetCollectionsStepDefs(StepData stepData) {
        globalDataUtils = stepData.globalDataUtils;
    }

    @When("request for getting new collection")
    public void requestForGettingNewCollection() {
        Response response = getRequest("/collections/" + globalDataUtils.getUID());
        globalDataUtils.setResponse(response);
    }

    @Then("verify the get response payload collection information")
    public void verifyTheGetResponsePayloadCollectionInformation() {
        Response response = globalDataUtils.getResponse();

        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(), is("application/json; charset=utf-8"));
        assertThat(response.path("collection.info.name"), is(globalDataUtils.getCollectionName()));
        assertThat(response.path("collection.info.schema"), is(ConfigurationReader.get("collection_schema")));
        deleteRequest("/collections/" + globalDataUtils.getUID());
    }

    @Then("verify the get response payload request information")
    public void verifyTheGetResponsePayloadRequestInformation() {
        Response response = globalDataUtils.getResponse();
        Item item = response.body().as(SingleCollection.class).getCollection().getItem().get(0);

        assertThat(item.getName(),is(globalDataUtils.getItem().getName()));
        assertThat(item.getRequest().getMethod(),is(globalDataUtils.getItem().getRequest().getMethod()));
        assertThat(item.getRequest().getUrl().getRaw(),is(globalDataUtils.getItem().getRequest().getUrl()));
    }

    @Given("multiple collections are created")
    public void multipleCollectionsAreCreated() {
        NewCollection newCollection1 = generateCollectionOnlyPOJO();
        NewCollection newCollection2 = generateCollectionOnlyPOJO();
        Response response1 = postRequest(newCollection1, "/collections");
        Response response2 = postRequest(newCollection2, "/collections");
        globalDataUtils.setCollections(Arrays.asList(newCollection1,newCollection2));
        globalDataUtils.setUIDs(Arrays.asList(response1.path("collection.uid"),response2.path("collection.uid")));
    }

    @When("request for getting all collections")
    public void requestForGettingAllCollections() {
        Response response = getRequest("collections");
        globalDataUtils.setResponse(response);
    }

    @Then("verify all collections information")
    public void verifyAllCollectionsInformation() {
        Response response = globalDataUtils.getResponse();
        List<NewCollection> expectedCollections = globalDataUtils.getCollections();
        AllCollections allCollections = response.body().as(AllCollections.class);

        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(), is("application/json; charset=utf-8"));
        assertThat(allCollections.getCollections().get(0).getCreatedAt(),is(LocalDate.now()));
        assertThat(allCollections.getCollections().get(1).getCreatedAt(),is(LocalDate.now()));
        assertThat(allCollections.getCollections().size(),is( greaterThanOrEqualTo( expectedCollections.size() )) );
        deleteRequest("/collections/" + globalDataUtils.getUIDs().get(0));
        deleteRequest("/collections/" + globalDataUtils.getUIDs().get(1));
    }

}
