package com.example.reservation.server.model;

import javafx.beans.property.*;

public abstract class Room {
    IntegerProperty roomID = new SimpleIntegerProperty(0);
    StringProperty roomType = new SimpleStringProperty();
    DoubleProperty roomRate = new SimpleDoubleProperty();

    public int getRoomID() {
        return roomID.get();
    }

    public String getRoomType() {
        return roomType.get();
    }
    public double getRoomRate() {
        return roomRate.get();
    }

    public void setRoomID(int id) {
        roomID.set(id);
    }
    public abstract void setRoomType();
    public void setRoomRate(double rate) {
        roomRate.set(rate);
    }
}
