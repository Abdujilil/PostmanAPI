package com.postman.step_definitions;

import com.postman.pojos.request.collections.NewCollection;
import com.postman.utilities.GlobalDataUtils;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.postman.utilities.APIUtils.*;
import static com.postman.utilities.DataUtils.*;

public class UpdateCollectionsStepDefs {

    private GlobalDataUtils globalDataUtils;

    public UpdateCollectionsStepDefs(StepData stepData) {
        globalDataUtils = stepData.globalDataUtils;
    }

    @When("request for updating collection information")
    public void requestForUpdatingCollectionInformation() {
        NewCollection newCollection = generateCollectionOnlyPOJO();
        Response response = putRequest(newCollection,"/collections/" + globalDataUtils.getUID());

        globalDataUtils.setCollectionName(newCollection.getCollection().getInfo().getName());
        globalDataUtils.setResponse(response);
        globalDataUtils.setUID(response.path("collection.uid"));
    }

    @When("request for updating collection and {string} request information")
    public void requestForUpdatingCollectionAndRequestInformation(String method) {
        NewCollection newCollection = generateCollectionPOJOWithReq(method);
        Response response = putRequest(newCollection,"/collections/" + globalDataUtils.getUID());

        globalDataUtils.setCollectionName(newCollection.getCollection().getInfo().getName());
        globalDataUtils.setItem(newCollection.getCollection().getItem().get(0));
        globalDataUtils.setResponse(response);
        globalDataUtils.setUID(response.path("collection.uid"));
    }

}
