package com.libraryCT.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static io.restassured.RestAssured.*;

public class Hooks {


    @Before
    public void init(){
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
    }

    @After
    public void tearDown(){reset();}



}
