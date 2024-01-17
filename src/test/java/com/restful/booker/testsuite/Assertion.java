package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class Assertion
{
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = given()
                .when()
                .get("/booking/104")
                .then().statusCode(200);
    }

    //Verify checkin is on 2018-01-01
    @Test
    public void test1()
    {
       response.body("bookingdates.checkin" ,equalTo("2018-01-01"));
    }


    //Verify totalprice is greater than  100
    @Test
    public void test2()
    {
        response.body("totalprice" ,greaterThan(100));
    }

    //Verify that the lastname is "Smith"
    @Test
    public void test3()
    {
        response.body("lastname" ,equalTo("Allen"));
    }

    //Verify that the additionalneeds is Breakfast
    @Test
    public void test4()
    {
        response.body("additionalneeds" ,equalTo("super bowls"));
    }
}
