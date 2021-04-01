package com.postman.utilities;

import com.postman.pojos.request.collections.Item;
import com.postman.pojos.request.collections.NewCollection;
import com.postman.pojos.request.environments.NewEnvironment;
import com.postman.pojos.request.environments.Value;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GlobalDataUtils {

    private Response response;
    private String collectionName;
    private String UID;
    private String itemName;
    private Item item;
    private String requestURL;
    private String requestMethod;
    private String requestDescription;
    private List<String> UIDs;
    private List<NewCollection> collections;
    private String environmentName;
    private Value value;
    private List<NewEnvironment> environments;

}
