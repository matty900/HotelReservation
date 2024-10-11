package com.example.reservation.server.model;

public class DeluxRoom extends Room{

    public DeluxRoom(int id, double rate) {
        roomID.set(id);
        roomRate.set(rate);
        setRoomType();
    }

    @Override
    public void setRoomType() {
        roomType.set("Delux Room");
    }
}
