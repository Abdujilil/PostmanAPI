package com.postman.step_definitions;

import com.postman.utilities.ConfigurationReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static io.restassured.RestAssured.*;

public class Hooks {

    @Before
    public void setUp() {
        baseURI = ConfigurationReader.get("base_uri");
    }

    @After
    public void tearDown() {
        reset();
    }

}
