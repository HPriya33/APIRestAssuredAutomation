package com.DummyAPIframework.asserts;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;

public class AssertActions {

    // Method to verify response body (String)
    public void verifyResponseBody(String actual, String expected, String description) {
        assertEquals(actual, expected, description);
    }

    // Method to verify response body (Integer)
    public void verifyResponseBody(int actual, int expected, String description) {
        assertEquals(actual, expected, description);
    }

    // Method to verify status code
    public void verifyStatusCode(Response response, Integer expected) {
        assertEquals(response.getStatusCode(), expected);
    }

    // Method to verify a string key using AssertJ
    public void verifyStringKey(String keyExpect, String keyActual) {
        assertThat(keyExpect).isNotNull();
        assertThat(keyExpect).isNotNull().isNotBlank();
        assertThat(keyExpect).isEqualTo(keyActual);
    }

    // Method to verify a non-null integer key using AssertJ
    public void verifyStringKeyNotNull(Integer keyExpect) {
        assertThat(keyExpect).isNotNull();
    }

    // Method to verify a non-null string key using AssertJ

    public void verifyStringKeyNotNull(String keyExpect){
        // AssertJ
        assertThat(keyExpect).isNotNull();
    }
}