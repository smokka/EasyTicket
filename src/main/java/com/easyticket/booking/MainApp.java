package com.easyticket.booking;

import com.easyticket.booking.model.BookingOrder;
import com.easyticket.booking.model.TheatreRow;
import com.easyticket.booking.service.InputReaderService;
import com.easyticket.booking.service.ReservationService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.List;

/**
 * Main class for Ticket booking system
 */
public class MainApp {
    public static void main(String[] args) {

        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(System.in));

        InputReaderService ioService = new InputReaderService(inputReader);
        ReservationService reservationService = new ReservationService();

        List<TheatreRow> layout = ioService.processTheatreLayOut();
        Deque<BookingOrder> bookingOrders = ioService.readBookingOrderDetails();

        System.out.println(reservationService.processBulkOrders(layout, bookingOrders).trim());

    }
}
