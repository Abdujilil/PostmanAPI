package com.postman.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;

import static io.restassured.RestAssured.given;

public class APIUtils {

    private static RequestSpecification requestSpec() {
        return given().log().all()
                      .accept(ContentType.JSON)
                      .contentType(ContentType.JSON)
                      .queryParam("workspace",ConfigurationReader.get("workspace_id"))
                      .header("X-Api-Key",ConfigurationReader.get("api_key"));
    }

    public static Response postRequest(Object body, String endPoint) {
        return given()
                     .spec(requestSpec())
                     .body(body)
               .when()
                      .post(endPoint)
                      .prettyPeek();
    }

    public static Response getRequest(String endPoint) {
        return given()
                .spec(requestSpec())
                .when()
                .get(endPoint)
                .prettyPeek();
    }

    public static Response getRequest(Map<String, Object> pathParams, String endPoint) {
        return given()
                      .spec(requestSpec())
                      .pathParams(pathParams)
               .when()
                      .get(endPoint)
                      .prettyPeek();
    }

    public static Response deleteRequest(String endPoint) {
        return given()
                    .spec(requestSpec())
                .when()
                    .delete(endPoint)
                    .prettyPeek();
    }

    public static Response deleteRequest(Map<String, Object> pathParams, String endPoint) {
        return given()
                      .spec(requestSpec())
                      .pathParams(pathParams)
               .when()
                      .delete(endPoint)
                      .prettyPeek();
    }

    public static Response putRequest(Object body, String endPoint) {
        return given()
                .spec(requestSpec())
                .body(body)
                .when()
                .put(endPoint)
                .prettyPeek();
    }

}
