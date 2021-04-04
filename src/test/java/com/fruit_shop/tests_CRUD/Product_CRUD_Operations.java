package com.fruit_shop.tests_CRUD;


import com.fruit_shop.utils.TestBase;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

public class Product_CRUD_Operations extends TestBase{



    @DisplayName("GET /products/")
    @Test
    public void getListOfProducts(){

        given()
                .log().uri()

                .when()
                .get("/products/")

                .then()

                .log().all()
                .statusCode(200)
                .body("products.name" , hasItem("Bananas") )
                 ;




    }


    @DisplayName("GET /vendors/")
    @Test
    public void getAllVendors_AddAProductToOne(){

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


    }




    @DisplayName("POST /vendors/{id}/products/   -> Add a new product for an existing vendor")
    @ParameterizedTest
    @MethodSource("getAllIDs")
    public void add_Product_To_EachVendor(Integer eachVendorID){
        //Add a new product for an existing vendor
        System.out.println("eachVendorID = " + eachVendorID);


        given()
                .log().uri()
                .pathParam("idOfEachVendor", eachVendorID)
                .contentType("application/json")
                .body(getRandomProduct())

                .when()
                .post("/vendors/{idOfEachVendor}/products/")

                .then()
                .log().all()
                .statusCode(201);



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


    public static Map<String,Object> getRandomProduct(){


        Faker faker1  = new Faker();

        Map<String, Object> fruitObject = new LinkedHashMap<>();
        String colorName = faker1.color().name();
        fruitObject.put("name", colorName.substring(0,1).toUpperCase()+colorName.substring(1) +" "+faker1.food().fruit() );
        fruitObject.put("price", faker1.number().numberBetween(1,100) );
        fruitObject.put("category", faker1.demographic().race() );


        return fruitObject;

    }









}
