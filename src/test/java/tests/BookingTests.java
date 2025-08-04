package tests;

import org.testng.annotations.Test;
import pojos.BookingDates;
import pojos.BookingInfo;

public class BookingTests {

    @Test
    public void verifyCreateBooking(){
        BookingInfo bookingInfo = new BookingInfo();

        BookingDates bookingDates= new BookingDates();
        bookingDates.setCheckin("2025-06-01");
        bookingDates.setCheckout("2025-06-02");



        bookingInfo.setFirstname("Bena");
        bookingInfo.setLastname("Pier");
        bookingInfo.setTotalprice(200);
        bookingInfo.setDepositpaid(true);
        bookingInfo.setAdditionalneeds("blanket");
        bookingInfo.setBookingDates(bookingDates);


    }
}
