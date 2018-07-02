package com.easyticket.booking.model;

/**
 * Model representing booking request
 */
public class BookingOrder {

    private String userName;
    private int seatsRequested;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSeatsRequested() {
        return seatsRequested;
    }

    public void setSeatsRequested(int seatsRequested) {
        this.seatsRequested = seatsRequested;
    }
}
