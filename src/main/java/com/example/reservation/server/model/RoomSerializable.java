package com.example.reservation.server.model;



import javafx.beans.property.*;

import java.io.Serializable;

public class RoomSerializable implements Serializable
{
    private int roomID;
    private String  roomType;
    private double rate;

    public RoomSerializable(int id, String type, double rate){
        this.roomID = id;
        this.roomType = type;
        this.rate = rate;
    }


    public int getRoomID() {
        return roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getRate() {
        return rate;
    }
}
