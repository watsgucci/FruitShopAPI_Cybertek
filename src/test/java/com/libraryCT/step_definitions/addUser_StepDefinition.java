package com.libraryCT.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class addUser_StepDefinition {

    String token = "";

    //POST /login  -> Login to get JWT Token
    @Given("Librarian user is successfully logged in with login end point")
    public void librarian_user_is_successfully_logged_in_with_login_end_point() {
        token =
        given()
                .log().uri()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S" )
                .accept("application/json")

                .when()
                .post("/login")

                .then()
                .log().all()
                .statusCode(200).extract().jsonPath().getString("token");

    }


    // POST /add_user -> Add User to system
    @When("Librarian adding an new user with an add user end point")
    public void librarian_adding_an_new_user_with_an_add_user_end_point() {

        Map<String, Object> userInfo = new LinkedHashMap<>();
        userInfo.put("full_name", "");
        userInfo.put("email", "");
        userInfo.put("password", "");
        userInfo.put("user_group_id", "");
        userInfo.put("status", "");
        userInfo.put("start_date", "");
        userInfo.put("end_date", "");
        userInfo.put("address", "");



        given()
                .log().uri()
                .header("x-library-token", token)

    }


    @Then("new user is successfully created with status code {int}")
    public void new_user_is_successfully_created_with_status_code(Integer int1) {

    }



}
