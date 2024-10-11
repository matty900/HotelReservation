package com.example.reservation.client.model;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Confirmation {

    private IntegerProperty reservationID;
    private StringProperty guestName;
    private IntegerProperty numGuests;
    private IntegerProperty numRooms;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private DoubleProperty ratePerDay;

    public Confirmation(int id, String name, int numGuests, int numRooms, LocalDateTime startDate, LocalDateTime endDate, double rate) {
        this.reservationID = new SimpleIntegerProperty(id);
        this.guestName = new SimpleStringProperty(name);
        this.numGuests = new SimpleIntegerProperty(numGuests);
        this.numRooms = new SimpleIntegerProperty(numRooms);
        this.startDate = startDate;
        this.endDate = endDate;
        this.ratePerDay = new SimpleDoubleProperty(rate);
    }

    public int getReservationID() {
        return reservationID.get();
    }

    public String getGuestName() {
        return guestName.get();
    }

    public int getNumGuests() {
        return numGuests.get();
    }

    public int getNumRooms() {
        return numRooms.get();
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double getRatePerDay() {
        return ratePerDay.get();
    }

}