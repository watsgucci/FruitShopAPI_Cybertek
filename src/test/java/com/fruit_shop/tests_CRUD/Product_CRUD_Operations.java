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








    //*****************************************************************************************
    //*****************************************************************************************
    //*****************************************************************************************
    //*****************************************************************************************


    //Update the name and price of the product
    @DisplayName("PATCH /products/{id}")
    @Test
    public void update_Name_and_Price_Of_Product(){

        JsonPath jp =
        given()
                .log().uri()

                .when()
                .get("/products/")

                .then()
                .log().all()
                .statusCode(200).extract().jsonPath();

        List<String> listOfProductURL = jp.getList("products.product_url", String.class);
        listOfProductURL.forEach(p-> System.out.println(p));

        List<Integer> listOfProductIDs = new LinkedList<>();
        listOfProductURL.forEach(p-> listOfProductIDs.add(Integer.parseInt(p.substring(p.lastIndexOf("/")+1))));

        System.out.println("==============================");
        listOfProductIDs.forEach(p-> System.out.println(p));



    }










}
