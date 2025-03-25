package com.DummyAPIframework.modules;
import com.DummyAPIframework.pojos.*;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class PayloadManager  {

    //Java object to JSON
    //Create the payload using any oprtion GSON or Jackson
    //here we are using GSON for payload
    Gson gson;

    //Create the payload as a string
    public String createPayloadBookingAsString() {
        //Payload and this is serilization
        //serialization refers to the process of converting a Java object into a
        // JSON or XML format that can be sent as the request body in an HTTP request (e.g., POST or PUT).
        // REST Assured simplifies
        // this process by automatically handling the serialization of Java objects into the desired format.


        Booking booking = new Booking();//create the pojo class object and set the values into the payload
        booking.setFirstname("Priyanka");// all values are set through using seeter method which is present in pojo classes.
        booking.setLastname("Hingmire");
        booking.setTotalprice(333);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Breakfast");

        Bookingdates bookingdates = new Bookingdates();//another sub-set that is booking dates.
        bookingdates.setCheckin("2024-02-01");//setting the date values
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingdates(bookingdates);//main object to set the values in booking dates.
        System.out.println(booking);
        //Now convert this JAVA ojbect into the JSON
        Gson gson = new Gson();
        String Jsonstringbooking = gson.toJson(booking);
        System.out.println(Jsonstringbooking);
        return Jsonstringbooking;
    }
        //Deserilization convert JSON response to JAV object again
        // Converting the String to the JAVA Object
    //Booking response is a pojo response class
        public BookingResponse bookingResponseJava (String responseString){
            gson = new Gson();
            BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
            return bookingResponse;
        }
    public String setAuthpayload() {
        //Payload and this is serilization
        //serialization refers to the process of converting a Java object into a
        // JSON or XML format that can be sent as the request body in an HTTP request (e.g., POST or PUT).
        // REST Assured simplifies
        // this process by automatically handling the serialization of Java objects into the desired format.


        Auth auth=new Auth();
        auth.setUsername("admin");// all values are set through using seeter method which is present in pojo classes.
      auth.setPassword("password123");

        //Now convert this JAVA ojbect into the JSON
        Gson gson = new Gson();
        String Jsonstringauth = gson.toJson(auth);
        System.out.println(Jsonstringauth);
        return Jsonstringauth;
    }
    //Deserilization convert JSON response to JAV object again
    // Converting the String to the JAVA Object
    //Booking response is a pojo response class
    public String gettokenresponse(String responseString){
        gson = new Gson();
        TokenRepsonse tokenResponse = gson.fromJson(responseString, TokenRepsonse.class);
        return tokenResponse.getToken().toString();
    }

    public Booking getResponseFromJSON(String getResponse){
        gson = new Gson();
        Booking booking = gson.fromJson(getResponse,Booking.class);
        return booking;
    }
    public String fullUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Lucky");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);


    }

    public List<GetAllBookingResponse> getAllBookingByIdResponse(String getAllbookingresponse){

        Gson gson = new Gson();
        Type bookingListType = new TypeToken<List<GetAllBookingResponse>>(){}.getType();
        List<GetAllBookingResponse> bookings = gson.fromJson(getAllbookingresponse, bookingListType);
        return bookings;
    }

    }



