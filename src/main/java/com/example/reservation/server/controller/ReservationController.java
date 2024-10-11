package com.example.reservation.server.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.reservation.server.model.*;

import java.util.HashMap;
import java.util.Map;

public class ReservationController
{
    private ObservableList<Room> allRooms;
    private HashMap<Integer, Reservation> reservations;
    private ObservableList<Reservation> allBookingsList;
    private ObservableList<Guest> allGuests;
    private HashMap<Integer, Bill> allBills;

    int reservationID = 1001;
    int guestID = 01;
    int billID = 1931;
    int roomID = 2001;

    public ReservationController(){
        reservations = new HashMap<>();
        allGuests = FXCollections.observableArrayList();
        allBills = new HashMap<>();
        allBookingsList = FXCollections.observableArrayList();
        allRooms = FXCollections.observableArrayList();
        initializeRooms();
    }

    public ObservableList<Reservation> getAllBookingsList() {
        return allBookingsList;
    }

    public ObservableList<Room> getAllRooms() {
        return allRooms;
    }

    public void addBooking(String title, String fName, String lName, String address, int phone, String email, int numGuests, String type, int numDays, double rate, int numRooms) {
        Guest guest = new Guest(title, fName, lName, address, phone, email);
        guest.setId(guestID);
        String guestName = fName+" "+lName;

        Reservation reservation = new Reservation(numGuests, guestName, type, numDays, rate, numRooms);
        reservation.setId(reservationID);

        guestID++;
        reservationID++;

        allGuests.add(guest);
        reservations.put(guest.getId(), reservation);
        allBookingsList.add(reservation);

        if(type.equals("Single")) {
            allRooms.remove(0);
        } else if(type.equals("Double")) {
            allRooms.remove(10);
        } else if(type.equals("Deluxe")) {
            allRooms.remove(20);
        } else {
            allRooms.remove(30);
        }
    }

    public void addBill(int rID, double amount) {
        Bill bill = new Bill(billID, rID, amount);
        billID++;
        allBills.put(rID, bill);
    }

    public Reservation getReservation(String email) {
        Guest foundGuest = null;

        for(Guest guest: allGuests) {
            if(guest.getEmail().equals(email))
                foundGuest = guest;
        }

        if(foundGuest == null)
            return null;

        return reservations.get(foundGuest.getId());
    }

    public HashMap<Integer, Bill> getAllBills() {
        return allBills;
    }

    public void initializeRooms() {
        for(int i = roomID; i<roomID+10; i++) {
            allRooms.add(new SingleRoom(i, 20));
        }
        roomID+=10;

        for(int i = roomID; i<roomID+10; i++) {
            allRooms.add(new DoubleRoom(i, 40));
        }
        roomID+=10;

        for(int i = roomID; i<roomID+10; i++) {
            allRooms.add(new DeluxRoom(i, 70));
        }
        roomID+=10;

        for(int i = roomID; i<roomID+10; i++) {
            allRooms.add(new Penthouse(i, 100));
        }
        roomID+=10;
    }
}
