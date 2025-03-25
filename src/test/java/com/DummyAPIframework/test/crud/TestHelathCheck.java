package com.DummyAPIframework.test.crud;

import com.DummyAPIframework.base.BaseTest;
import com.DummyAPIframework.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestHelathCheck extends BaseTest {
    @Test(groups = "reg", priority = 1)
    @TmsLink("https://bugz.atlassian.net/browse/TS-1")
    @Owner("Priyanka")
    @Description("TC#INT3 - verify Helathcheck")
    public void Helathcheck() {
        //This is the Prepration of the Url
        requestSpecification.basePath(APIConstants.PING_URL);//passing the base bath i.e crete or update
        //This is Making the requests
        response = RestAssured.given(requestSpecification)
                .when()
                .get();
        //This is the Validation of the request
        validatableResponse = response.then().log().all();//response validation
        validatableResponse.statusCode(201); //validation of status code

    }
}
