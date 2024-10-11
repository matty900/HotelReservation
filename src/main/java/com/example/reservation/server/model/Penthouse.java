package com.example.reservation.server.model;

public class Penthouse extends Room{

    public Penthouse(int id, double rate) {
        roomID.set(id);
        roomRate.set(rate);
        setRoomType();
    }

    @Override
    public void setRoomType() {
        roomType.set("Penthouse");
    }
}
