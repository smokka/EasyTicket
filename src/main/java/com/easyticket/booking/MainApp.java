package com.easyticket.booking;

import com.easyticket.booking.model.BookingOrder;
import com.easyticket.booking.model.TheatreRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Main class for Ticket booking system
 */
public class MainApp {
    public static void main(String[] args) {

        BufferedReader inputReader;
        try {
            inputReader = new BufferedReader(
                    new InputStreamReader(System.in));

            List<TheatreRow> theatreLayout = processTheatreLayOut(inputReader);
            List<BookingOrder> bookingOrders = ReadBookingOrderDetails(inputReader);

            inputReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the input and set the details of theatre row and sections
     *
     * @param inputReader
     * @throws IOException
     */
    private static List<TheatreRow> processTheatreLayOut(BufferedReader inputReader) throws IOException {
        int i = 0;
        List<TheatreRow> theatreRows = new ArrayList<>();
        while (true) {
            String currentLine = inputReader.readLine();

            if (currentLine.trim().isEmpty())
                break;

            StringTokenizer tokenizer = new StringTokenizer(currentLine);
            List<Integer> sections = new ArrayList<>();
            while (tokenizer.hasMoreTokens()) {
                sections.add(Integer.parseInt(tokenizer.nextToken());
            }
            theatreRows.add(new TheatreRow(i, sections));
            i++;
        }
        return theatreRows;
    }

    /**
     * @param inputReader
     * @return
     * @throws IOException
     */
    private static List<BookingOrder> ReadBookingOrderDetails(BufferedReader inputReader) throws IOException {
        List<BookingOrder> bookingOrders = new ArrayList<>();

        while (true) {
            String currentLine = inputReader.readLine();
            if (currentLine.trim().isEmpty())
                break;
            BookingOrder bookingOrder = new BookingOrder();
            StringTokenizer tokenizer = new StringTokenizer(currentLine);
            bookingOrder.setUserName(tokenizer.nextToken());
            bookingOrder.setSeatsRequested(Integer.parseInt(tokenizer.nextToken()));
            bookingOrders.add(bookingOrder);
        }
        return bookingOrders;

    }
}
}
