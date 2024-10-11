package com.example.reservation.server.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Bill
{
    private IntegerProperty billID;
    private IntegerProperty reservationId;

    private DoubleProperty amountToPay;

    public Bill(int bID, int rID, double amount) {
        this.billID = new SimpleIntegerProperty(bID);
        this.reservationId = new SimpleIntegerProperty(rID);
        this.amountToPay = new SimpleDoubleProperty(amount);
    }

    public int getBillID() {
        return billID.get();
    }

    public IntegerProperty billIDProperty() {
        return billID;
    }

    public int getReservationId() {
        return reservationId.get();
    }

    public IntegerProperty reservationIdProperty() {
        return reservationId;
    }

    public double getAmountToPay() {
        return amountToPay.get();
    }

    public DoubleProperty amountToPayProperty() {
        return amountToPay;
    }
}
