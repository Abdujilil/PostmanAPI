package com.postman.utilities;

import com.postman.pojos.request.collections.*;
import com.postman.pojos.request.environments.Environment;
import com.postman.pojos.request.environments.NewEnvironment;
import com.postman.pojos.request.environments.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataUtils {

    public static String generateTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMdhhmmsSSS"));
    }

    public static String generateName(String  nameType) {
        return "Sample " + nameType + " " + generateTimeStamp();
    }

    public static NewCollection generateCollectionOnlyPOJO() {
        String collectionName = generateName("Collection");
        Info info = new Info(collectionName, ConfigurationReader.get("collection_schema"));
        Collection collection = new Collection(info, new ArrayList<>());
        return new NewCollection(collection);
    }

    public static NewCollection generateCollectionPOJOWithReq(String method) {
        String collectionName = generateName("Collection");
        Info info = new Info(collectionName, ConfigurationReader.get("collection_schema"));
        Request request = new Request("https://postman-echo/" + method.toLowerCase(),method);
        Item item1 = new Item("Sample " + method + " Request",request);
        List<Item> item = new ArrayList<>(Arrays.asList(item1));
        com.postman.pojos.request.collections.Collection collection = new Collection(info, item);
        return new NewCollection(collection);
    }

    public static NewEnvironment generateEmptyEnvironmentPOJO() {
        String environmentName = generateName("Environment");
        Environment environment = new Environment(environmentName);
        return new NewEnvironment(environment);
    }

    public static NewEnvironment generateEnvironmentWithVariablePOJO() {
        String environmentName = generateName("Environment");
        Value value1 = new Value(generateName("Variable"),generateName("Value"));
        List<Value> values = new ArrayList<>(Arrays.asList(value1));
        Environment environment = new Environment(environmentName, values);
        return new NewEnvironment(environment);
    }

}
