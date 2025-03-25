package com.DummyAPIframework.test.integration;

import com.DummyAPIframework.base.BaseTest;
import com.DummyAPIframework.endpoints.APIConstants;
import com.DummyAPIframework.pojos.Booking;
import com.DummyAPIframework.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestIntegrationFlowE2E_01 extends BaseTest {

//  Test E2E Scenario 1

    //  1. Create a Booking -> bookingID

    // 2. Create Token -> token

    // 3. Verify that the Create Booking is working - GET Request to bookingID

    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

    // 5. Delete the Booking - Need to get the token, bookingID from above request


    // Create A Booking, Create a Token
    // Verify that Get booking -
    // Update the Booking
    // Delete the Booking

    @Test(groups = "qa", priority = 1)
    @Owner("Priyanka")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){
//This is the Prepration of the Url
        //we want a booking id here so we can not return any return type so we are using
        //testng Icontextinterface for geeting the values
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
    @Test(groups = "qa", priority = 2)
    @Owner("Priyanka")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
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
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Priyanka");

    }

    @Test(groups = "qa", priority = 3)
    @Owner("Priyanka")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){
     //   Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token",token);

        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);

        response = RestAssured
                .given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();


        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Lucky");
        assertThat(booking.getLastname()).isEqualTo("Dutta");

    }

    @Test(groups = "qa", priority = 4)
    @Owner("Priyanka")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        //String token = (String)iTestContext.getAttribute("token");
       // Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        String token = getToken();

        requestSpecification.basePath(basePathDELETE);
        response = RestAssured.given(requestSpecification)
                .when().cookie("token", token).delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
        validatableResponse.statusLine("HTTP/1.1 201 Created");

    }
}
