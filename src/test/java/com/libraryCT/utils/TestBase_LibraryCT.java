package com.libraryCT.utils;


import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class TestBase_LibraryCT {

// http://library1.cybertekschool.com/rest/v1
    @BeforeAll
    public static void init(){
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }


}
