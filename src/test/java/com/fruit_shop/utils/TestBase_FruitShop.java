package com.fruit_shop.utils;



import static io.restassured.RestAssured.*;


import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestBase_FruitShop {


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



    public static Map<String,Object> getRandomProduct(){


        Faker faker1  = new Faker();

        Map<String, Object> fruitObject = new LinkedHashMap<>();
        String colorName = faker1.color().name();
        fruitObject.put("name", colorName.substring(0,1).toUpperCase()+colorName.substring(1) +" "+faker1.food().fruit() );
        fruitObject.put("price", faker1.number().numberBetween(1,100) );
        fruitObject.put("category", faker1.demographic().race() );


        return fruitObject;

    }

    public static List<Integer> getAllIDs(){
        JsonPath jp =
                given()
                        .log().uri()

                        .when()
                        .get("/vendors/")

                        .then()

                        .log().all()
                        .statusCode(200)
                        .extract().jsonPath();



        System.out.println("==============================================");
        System.out.println();
        List<String> allVendorURL = jp.getList("vendors.vendor_url");
        allVendorURL.forEach(p-> System.out.println(p));

        List<Integer> allIDs = new LinkedList<>();
        allVendorURL.forEach(p-> allIDs.add(Integer.parseInt(p.substring(p.lastIndexOf("/")+1))));
        allIDs.forEach(p-> System.out.println(p));

        return allIDs;
    }




}
