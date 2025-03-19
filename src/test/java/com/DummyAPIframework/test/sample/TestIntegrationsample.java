package com.DummyAPIframework.test.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationsample {
    // Create A Booking, Create a Token
    // Verify that Get booking -
    // Update the Booking
    // Delete the Booking
    //creating a dummy testcase

    //This is the healthcheck of the dependencies means that is working or not.

    @Test(groups = "qa", priority = 1)
    @Owner("Priyanka")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(){
        Assert.assertTrue(true);
    }
    @Test(groups = "qa", priority = 2)
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
