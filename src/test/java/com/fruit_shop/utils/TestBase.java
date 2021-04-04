package com.fruit_shop.utils;



import static io.restassured.RestAssured.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {


    @BeforeAll
    public static void init(){
        //https://api.predic8.de/shop/
        baseURI = "https://api.predic8.de";
        basePath = "/shop";
    }

    @AfterAll
    public static void cleanup(){
        reset();
    }





}
