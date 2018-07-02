package com.easyticket.booking.service;

import com.easyticket.booking.model.BookingOrder;
import com.easyticket.booking.model.TheatreRow;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ReservationServiceTest {

    List<TheatreRow> layout = new ArrayList<>();
    Deque<BookingOrder> orders = new LinkedList<>();

    @Test
    public void Should_Validate_Seats_Beyond_Capacity() {

        layout.add(new TheatreRow(0, new ArrayList<Integer>() {{
            add(6);
            add(6);
        }}));

        orders.add(new BookingOrder(){{setSeatsRequested(200);setUserName("Jon");}});
        String response = new ReservationService().processBulkOrders(layout,orders);
        Assert.assertTrue(response.trim().equalsIgnoreCase("Jon Sorry, we can't handle your party."));
    }

    @Test
    public void Should_Validate_Split_Condition() {

        layout.add(new TheatreRow(0, new ArrayList<Integer>() {{
            add(5);
            add(5);
        }}));

        orders.add(new BookingOrder(){{setSeatsRequested(10);setUserName("Jon");}});
        String response = new ReservationService().processBulkOrders(layout,orders);
        Assert.assertTrue(response.trim().equalsIgnoreCase("Jon Call to split party."));
    }

    @Test
    public void Should_Work_For_Positive_Scenario() {

        layout.add(new TheatreRow(0, new ArrayList<Integer>() {{
            add(6);
            add(6);
        }}));

        orders.add(new BookingOrder(){{setSeatsRequested(4);setUserName("Jon");}});
        String response = new ReservationService().processBulkOrders(layout,orders);
        Assert.assertTrue(response.trim().equalsIgnoreCase("Jon Row 1 Section 1"));
    }
}