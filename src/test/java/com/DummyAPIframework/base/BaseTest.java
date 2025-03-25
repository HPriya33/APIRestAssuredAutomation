package com.DummyAPIframework.base;

import com.DummyAPIframework.asserts.AssertActions;
import com.DummyAPIframework.endpoints.APIConstants;
import com.DummyAPIframework.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    //The things are common to all Testcases.
    //Base URALs,ContectType,JSON
    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setUp() {
        // Base URL, Content Type - json
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
//This is the normal nonBDD style to setup
        //and here we are using Restassured methods
//        requestSpecification = RestAssured.given();
//        requestSpecification.baseUri(APIConstants.BASE_URL);
//        requestSpecification.contentType(ContentType.JSON).log().all();

//This is using RequestSpecBuilder class to set the URL
        //here we are using the class
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();
    }
//Commenly used function in the whole project thats why writing the method here
    public String getToken() {

        requestSpecification = RestAssured
                .given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);

        // Setting the payload
        String payload = payloadManager.setAuthpayload();

        // Get the Token
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();
        // String Extraction
      //  String token = payloadManager.getResponseFromJSON(response.asString());
        String token=payloadManager.gettokenresponse(response.asString());
        return token;
    }
}