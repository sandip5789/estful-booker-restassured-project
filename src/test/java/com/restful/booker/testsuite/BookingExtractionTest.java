package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookingExtractionTest
{
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = given()
                .when()
                .get("/booking")
                .then().statusCode(200);
    }

    //Extract the value of the  lastname

    @Test
    public void test1()
    {
        List<String> lastName = response.extract().path("booking.lastname");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of lastname is  : " + lastName);
        System.out.println("------------------End of Test---------------------------");

    }

    //Extract the length of the data
    @Test
    public void test2() {

        //List.size

        List<Integer> listOfData = response.extract().path("data.length");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : " + listOfData .size());
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract Booking ID from the lists

    @Test
    public void test3() {

        List<Integer> bookingId = response.extract().path("bookingid");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Booking ID  is : " + bookingId);
        System.out.println("------------------End of Test---------------------------");
    }
}


