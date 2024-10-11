package com.example.reservation.server.model;


import javafx.beans.property.*;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Reservation implements Serializable
{
    private int id;
    private int numGuests;
    private String roomType;

    private String guestName;
    private int numDays;
    private double rate;
    private int numRooms;

    private LocalDateTime checkinDate;

    private LocalDateTime checkoutDate;

    public Reservation(int numGuests, String name, String type, int numDays, double rate, int numRooms) {
        this.id = 0;
        this.guestName = name;
        this.numGuests = numGuests;
        this.numDays = numDays;
        this.numRooms = numRooms;
        this.roomType = type;
        this.rate = rate;
        this.checkinDate =  LocalDateTime.now();
        this.checkoutDate = LocalDateTime.of(checkinDate.getYear(), checkinDate.getMonth(), checkinDate.getDayOfMonth()+numDays, checkinDate.getHour(), checkinDate.getMinute());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumGuests() {
        return numGuests;
    }


    public String getRoomType() {
        return roomType;
    }

    public int getNumDays() {
        return numDays;
    }

    public double getRate() {
        return rate;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public LocalDateTime getCheckinDate() {
        return checkinDate;
    }

    public LocalDateTime getCheckoutDate() {
        return checkoutDate;
    }

    public String getGuestName() {
        return guestName;
    }
}
