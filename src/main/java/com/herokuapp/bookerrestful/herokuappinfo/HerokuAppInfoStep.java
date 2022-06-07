package com.herokuapp.bookerrestful.herokuappinfo;

import com.herokuapp.bookerrestful.constants.EndPoints;
import com.herokuapp.bookerrestful.model.HerokuAppPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.seleniumhq.jetty9.io.EndPoint;

import java.util.HashMap;

import static net.serenitybdd.rest.SerenityRest.then;

public class HerokuAppInfoStep {
    @Step
    public ValidatableResponse authUser(String username, String password) {

        HerokuAppPojo herokuAppPojo = new HerokuAppPojo();
        herokuAppPojo.setUsername(username);
        herokuAppPojo.setPassword(password);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(herokuAppPojo)
                .when()
                .post(EndPoints.CREATE_AUTH)
                .then();

    }

    @Step("Create Booking  firstname: {0}, lastname: {1}, totalprice: {2}, depositepaid: {3}, bookingsDates:{4}, addtionalsneeds : {5} ")
    public ValidatableResponse createBooking(String firstname,
                                             String lastname,
                                             int totalprice,
                                             boolean depositpaid,
                                             HashMap<Object, Object> bookingsDatesData,
                                             String additionalneeds) {
        HerokuAppPojo herokuappPojo = new HerokuAppPojo();
        herokuappPojo.setFirstname(firstname);
        herokuappPojo.setLastname(lastname);
        herokuappPojo.setTotalprice(totalprice);
        herokuappPojo.setDepositpaid(depositpaid);
        herokuappPojo.setBookingdates(bookingsDatesData);
        herokuappPojo.setAdditionalneeds(additionalneeds);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(herokuappPojo)
                .when()
                .post(EndPoints.GET_BOOKING)
                .then();
    }

    @Step("Update Booking  BookingID: {0},firstname: {1}, lastname: {2}, totalprice: {3}, depositepaid: {4}, bookingsDates:{5}, addtionalsneeds : {6} ")
    public ValidatableResponse updateBooking(int bookingID,
                                             String firstname,
                                             String lastname,
                                             int totalprice,
                                             boolean depositpaid,
                                             HashMap<Object, Object> bookingsDatesData,
                                             String additionalneeds) {
        HerokuAppPojo herokuappPojo = new HerokuAppPojo();
        herokuappPojo.setFirstname(firstname);
        herokuappPojo.setLastname(lastname);
        herokuappPojo.setTotalprice(totalprice);
        herokuappPojo.setDepositpaid(depositpaid);
        herokuappPojo.setBookingdates(bookingsDatesData);
        herokuappPojo.setAdditionalneeds(additionalneeds);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(herokuappPojo)
                .pathParam("bookingID",bookingID)
                .auth().preemptive().basic("admin","password123")
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step("Delete Booking  BookingID: {0}")
    public ValidatableResponse deleteBooking(int bookingID) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("bookingID",bookingID)
                .auth().preemptive().basic("admin","password123")
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }

    @Step("Get Booking by BookingID: {0}")
    public ValidatableResponse getBookingByID(int bookingID) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("bookingID",bookingID)
                .when()
                .get(EndPoints.GET_BOOKING_BY_ID)
                .then();
    }


}
