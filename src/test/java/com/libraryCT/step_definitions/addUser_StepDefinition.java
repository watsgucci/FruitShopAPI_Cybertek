package com.libraryCT.step_definitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        Faker faker = new Faker();

        Map<String,Object> myBookMap = new LinkedHashMap<>();

        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd");

        myBookMap.put("full_name",faker.name().fullName());
        myBookMap.put("email",faker.internet().emailAddress());
        myBookMap.put("password",faker.internet().password(8,15));
        myBookMap.put("user_group_id",faker.number().numberBetween(2,3));
        myBookMap.put("status","ACTIVE");
        myBookMap.put("start_date",df.format(LocalDate.now()));
        myBookMap.put("end_date",df.format(LocalDate.now().plusMonths(2)));
        myBookMap.put("address",faker.address().fullAddress());




        given()
                .log().uri()
                .header("x-library-token", token)
                .contentType(ContentType.JSON)
                .body(myBookMap)
                .accept(ContentType.JSON)

                .when()
                .post("/add_user")

                .then()
                .log().all()
                .statusCode(200)
                .body("message", is("The user has been created."));






    }


    @Then("new user is successfully created with status code {int}")
    public void new_user_is_successfully_created_with_status_code(Integer int1) {

    }



}
