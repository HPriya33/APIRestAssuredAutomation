package com.DummyAPIframework.test.integration;

import com.DummyAPIframework.base.BaseTest;
import com.DummyAPIframework.endpoints.APIConstants;
import com.DummyAPIframework.pojos.Booking;
import com.DummyAPIframework.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestFullFlowE2E_Task2 extends BaseTest {
    //1.Create the Booking using below request and id will be created.
    @Test(groups = "qa", priority = 1)
    @Owner("Priyanka")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext) {
//This is the Prepration of the Url
        //we want a booking id here so we can not return any return type so we are using
        //we are using the same Booking id everywhere so testng interface provides a special interface to use it
        //testng Icontextinterface for getting the values
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);//passing the base bath i.e crete or update
        //This is Making the requests
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString())
                .post();
        //This is the Validation of the request
        validatableResponse = response.then().log().all();//response validation
        validatableResponse.statusCode(200); //validation of status code
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Priyanka");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());

    }
    //2.Delete the created booking
    @Test(groups = "qa", priority = 2)
    @Owner("Priyanka")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        String token = getToken();

        requestSpecification.basePath(basePathDELETE);
        response = RestAssured.given(requestSpecification)
                .when().cookie("token", token).delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
        validatableResponse.statusLine("HTTP/1.1 201 Created");

    }
    //3.Trying to get the information after deletaion
    @Test(groups = "qa", priority = 3)
    @Owner("Priyanka")
    @Description("TC#INT1 - Step 2. Verify to get the records")
    public void testVerifyBookingId(ITestContext iTestContext){
        //needs to convert into the integer
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        // GET Request - to verify that the firstname after creation is James
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" + bookingid;
        System.out.println(basePathGET);
        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(404);

        //Booking booking = payloadManager.getResponseFromJSON(response.asString());
       // assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        //assertThat(booking.getFirstname()).isEqualTo("Priyanka");

    }
}
