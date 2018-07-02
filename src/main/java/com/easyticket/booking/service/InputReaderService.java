package com.easyticket.booking.service;

import com.easyticket.booking.model.BookingOrder;
import com.easyticket.booking.model.TheatreRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class InputReaderService {

    private BufferedReader inputReader;

    public InputReaderService(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

    /**
     * Read the input and set the details of theatre row and sections
     * @return List<TheatreRow>
     */
    public List<TheatreRow> processTheatreLayOut() {
        int i = 0;
        List<TheatreRow> theatreRows = new ArrayList<>();
        while (true) {
            String currentLine = getCurrentLine(inputReader);

            if (!currentLine.trim().isEmpty()) {
                StringTokenizer tokenizer = new StringTokenizer(currentLine);
                List<Integer> sections = new ArrayList<>();
                while (tokenizer.hasMoreTokens()) {
                    sections.add(Integer.parseInt(tokenizer.nextToken()));
                }
                theatreRows.add(new TheatreRow(i, sections));
                i++;
            }else {
                break;
            }
        }
        return theatreRows;
    }

    /**
     * Reads user booking requests
     * @return BookingOrderQueue
     */
    public Deque<BookingOrder> readBookingOrderDetails() {
        Deque<BookingOrder> bookingOrders = new LinkedList<>();

        while (true) {
            String currentLine = getCurrentLine(inputReader);
            if (!currentLine.trim().isEmpty()) {
                BookingOrder bookingOrder = new BookingOrder();
                StringTokenizer tokenizer = new StringTokenizer(currentLine);
                bookingOrder.setUserName(tokenizer.nextToken());
                bookingOrder.setSeatsRequested(Integer.parseInt(tokenizer.nextToken()));
                bookingOrders.addLast(bookingOrder);
            }else{
                break;
            }
        }
        return bookingOrders;
    }

    /**
     * Read current line as handle exceptions
     * @param inputReader
     * @return
     */
    private String getCurrentLine(BufferedReader inputReader) {
        String currentLine = null;
        try {
            currentLine = inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentLine;
    }
}
