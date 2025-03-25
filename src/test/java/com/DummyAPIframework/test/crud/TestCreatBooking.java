package com.DummyAPIframework.test.crud;

import com.DummyAPIframework.base.BaseTest;
import com.DummyAPIframework.endpoints.APIConstants;
import com.DummyAPIframework.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
//Extending BaseTest class becuse in the bse class we have all the setupdata
public class TestCreatBooking extends BaseTest {
 //Create A Booking, Create a Token
    // Verify that Get booking -
    // Update the Booking
    // Delete the Booking

    @Test(groups = "reg", priority = 1)
    @TmsLink("https://bugz.atlassian.net/browse/TS-1")
    @Owner("Priyanka")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
public void testCreateBooking(){
        //This is the Prepration of the Url
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
}




@Test(groups = "reg", priority = 2)
@Owner("Priyanka")
@Description("TC#INT1 - Step 2. Verify that the Booking By ID")
public void testVerifyBookingId(){
    Assert.assertTrue(true);
}

@Test(groups = "qa", priority = 3)
@Owner("Priyanka")
@Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
public void testUpdateBookingByID(){
    Assert.assertTrue(true);
}

@Test(groups = "qa", priority = 4)
@Owner("Priyanka")
@Description("TC#INT1 - Step 4. Delete the Booking by ID")
public void testDeleteBookingById(){
    Assert.assertTrue(true);
}

}
