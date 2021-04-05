package com.libraryCT.step_definitions;

import com.github.javafaker.Faker;
import com.libraryCT.utils.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class addUser_ExcelFile_StepDefinition {

    String token = "";

    //POST /login  -> Login to get JWT Token
    @Given("Librarian user is successfully logged in with login end point_ExcelFile")
    public void librarian_user_is_successfully_logged_in_with_login_end_point_ExcelFile() {
        token =
                given()
                        .log().uri()
                        .contentType(ContentType.URLENC)
                        .formParam("email", "librarian69@library")
                        .formParam("password", "KNPXrm3S")
                        .accept("application/json")

                        .when()
                        .post("/login")

                        .then()
                        .log().all()
                        .statusCode(200).extract().jsonPath().getString("token");

    }


    Integer userIDOfCreatedUser = 0;

    // POST /add_user -> Add User to system
    @When("Librarian adding an new user with an add user end point_ExcelFile")
    public void librarian_adding_an_new_user_with_an_add_user_end_point_ExcelFile() throws IOException {

//        Faker faker = new Faker();
//
//        Map<String,Object> myBookMap = new LinkedHashMap<>();
//
//        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        myBookMap.put("full_name",faker.name().fullName());
//        myBookMap.put("email",faker.internet().emailAddress());
//        myBookMap.put("password",faker.internet().password(8,15));
//        myBookMap.put("user_group_id",faker.number().numberBetween(2,3));
//        myBookMap.put("status","ACTIVE");
//        myBookMap.put("start_date",df.format(LocalDate.now()));
//        myBookMap.put("end_date",df.format(LocalDate.now().plusMonths(2)));
//        myBookMap.put("address",faker.address().fullAddress());


        List<Map<String, String>> listOfHundredUsers = ExcelReader.listOfMaps("libraryUsers.xlsx", "data");


        for (int i = 0; i < listOfHundredUsers.size(); i++) {

            given()
                    .log().uri()
                    .header("x-library-token", token)
                    .contentType(ContentType.JSON)
                    .body(listOfHundredUsers.get(i))
                    .accept(ContentType.JSON)

                    .when()
                    .post("/add_user")

                    .then()
                    .log().all()
                    .statusCode(200);
        }


    }

    // GET /get_user_by_id/{id}   -> Get User By ID
    @Then("new user is successfully created with status code {int}_ExcelFile")
    public void new_user_is_successfully_created_with_status_code_ExcelFile(Integer int1) {
//        System.out.println("==================================");
//        System.out.println("==================================");
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//        given()
//                .log().uri()
//                .pathParam("id", userIDOfCreatedUser)
//                .header("x-library-token", token)
//                .accept(ContentType.JSON)
//
//                .when()
//                .get("/get_user_by_id/{id}")
//
//                .then()
//                .log().all()
//                .statusCode(int1);
////                .body("id", is(equalTo(userIDOfCreatedUser.toString())));


    }


}
