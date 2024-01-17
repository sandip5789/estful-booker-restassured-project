package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BookingCRUDTest extends TestBase {

    @Test
    public void getAllBookingIDs() {
        Response response = given()
                .when()
                .get();
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getSingleBookingIDs() {
        Response response = given()
                .when()
                .get("/48");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void createBooking() {
        String firstName = TestUtils.getRandomValue() + "Virat";
        String lastName = TestUtils.getRandomValue() + "Kohli";
        HashMap<String, String> bookingDates = new HashMap();
        bookingDates.put("checkIn", "2024-03-04");
        bookingDates.put("checkOut", "2024-04-04");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName(firstName);
        bookingPojo.setLastName(lastName);
        bookingPojo.setTotalPrice(123);
        bookingPojo.setDepositPaid(true);
        bookingPojo.setAdditionalNeeds("Dinner");
        bookingPojo.setBookingDates(bookingDates);

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .header("cookie", "token=cfc999198079c38")
                        .body(bookingPojo)
                        .when()
                        .post("/booking");
        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void updateBooking() {

        String firstName = TestUtils.getRandomValue() + "Virat";
        String lastName = TestUtils.getRandomValue() + "Kohli";

        HashMap<String, String> bookingDates = new HashMap();
        bookingDates.put("checkIn", "2024-03-04");
        bookingDates.put("checkOut", "2024-04-04");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName(firstName);
        bookingPojo.setLastName(lastName);
        bookingPojo.setTotalPrice(123);
        bookingPojo.setDepositPaid(true);
        bookingPojo.setAdditionalNeeds("Dinner");
        bookingPojo.setBookingDates(bookingDates);

        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .auth().preemptive().basic("admin", "password123")
                        .body(bookingPojo)
                        .when()
                        .put("/booking/48");
        response.then().statusCode(404);
        response.prettyPrint();
    }

    @Test
    public void partialUpdateBooking() {
        String firstName = TestUtils.getRandomValue() + "Virat";
        String lastName = TestUtils.getRandomValue() + "Kohli";

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName(firstName);
        bookingPojo.setLastName(lastName);

        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .body(bookingPojo)
                        .when()
                        .patch("/104");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteBooking() {
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete("/38");
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void pingCheck() {
        Response response = given().log().all()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .get("https://restful-booker.herokuapp.com/ping");
        response.prettyPrint();
        response.then().statusCode(201);
    }
}
