package com.DummyAPIframework.endpoints;

public class APIConstants {
    public static String BASE_URL = "https://restful-booker.herokuapp.com";//This is hardcoded
    // public static String BASE_URL = // Fetch this from Excel file
    public static String CREATE_UPDATE_BOOKING_URL  = "/booking";
    public static String AUTH_URL  = "/auth";
    public static String PING_URL  = "/ping";
//why variables are static because we don't want to create the object and we can directly
    //use in the very class without objcet creation.
}
