package com.easyticket.booking.service;

import com.easyticket.booking.model.BookingOrder;
import com.easyticket.booking.model.TheatreRow;

import java.util.Deque;
import java.util.List;

public class ReservationService {
    int availableSeats;

    /**
     * Process booking for bulk orders
     * @param theatreRows
     * @param orders
     * @return
     */
    public String processBulkOrders(List<TheatreRow> theatreRows, Deque<BookingOrder> orders) {
        String bookingDetails = "";
        availableSeats = getTotalSeats(theatreRows);

        while (!orders.isEmpty()) {
            bookingDetails += ProcessSingleOrder(theatreRows, orders.removeFirst(), bookingDetails)+"\n";
        }

        return bookingDetails;
    }

    /**
     * Process booking for single Order
     * @param theatreRows
     * @param bookingOrder
     * @param bookingDetails
     * @return
     */
    private String ProcessSingleOrder(List<TheatreRow> theatreRows, BookingOrder bookingOrder, String bookingDetails) {

        for (int rowId = 0; rowId < theatreRows.size(); rowId++) {
            TheatreRow row =  theatreRows.get(rowId);
            for (int sectionId = 0; sectionId < row.getSections().size(); sectionId++) {
                int seatsInCurrentSection = row.getSections().get(sectionId);
                if (bookingOrder.getSeatsRequested() <= seatsInCurrentSection) {
                    // reset seat size in a section
                    row.getSections().set(sectionId, seatsInCurrentSection - bookingOrder.getSeatsRequested());
                    availableSeats-=bookingOrder.getSeatsRequested();

                    return createBookingResponse(bookingOrder, rowId+1, sectionId+1);
                }
            }
        }

        // if order cannot be fit in existing layout check alternatives
        if(bookingOrder.getSeatsRequested() <= availableSeats){
            return  bookingOrder.getUserName()+" Call to split party.";
        }else{
            return  bookingOrder.getUserName()+" Sorry, we can't handle your party.";
        }
    }

    /**
     * Calculate Sum of total number of seats available in Theatre
     * @param theatreRows
     * @return
     */
    private int getTotalSeats(List<TheatreRow> theatreRows) {
        int totalSeats = 0;
        for (TheatreRow row : theatreRows) {
            totalSeats += row.getSections().stream().reduce(0, Integer::sum);
        }
        return totalSeats;
    }

    /**
     * Create Response for Successful booking order
     * @param order
     * @param rowId
     * @param sectionId
     * @return
     */
    private String createBookingResponse(BookingOrder order, int rowId, int sectionId) {
        return order.getUserName() + " Row " + rowId + " Section " + sectionId;
    }
}
