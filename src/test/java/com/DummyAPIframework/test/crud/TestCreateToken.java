package com.DummyAPIframework.test.crud;

import com.DummyAPIframework.base.BaseTest;
import com.DummyAPIframework.endpoints.APIConstants;
import com.DummyAPIframework.pojos.BookingResponse;
import com.DummyAPIframework.pojos.TokenRepsonse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {
    @Test(groups = "reg", priority = 1)
    @TmsLink("https://bugz.atlassian.net/browse/TS-1")
    @Owner("Priyanka")
    @Description("TC#INT2 - Create token and verify")
    public void testToken() {
        //This is the Prepration of the Url
        requestSpecification.basePath(APIConstants.AUTH_URL);//passing the base bath i.e crete or update
        //This is Making the requests
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.setAuthpayload())
                .post();
        //This is the Validation of the request
        validatableResponse = response.then().log().all();//response validation
        validatableResponse.statusCode(200); //validation of status code
        String token = payloadManager.gettokenresponse(response.asString());

        assertActions.verifyStringKeyNotNull(token);
    }
}