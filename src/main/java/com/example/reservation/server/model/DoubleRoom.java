package com.example.reservation.server.model;
public class DoubleRoom extends Room{

    public DoubleRoom(int id, double rate) {
        roomID.set(id);
        roomRate.set(rate);
        setRoomType();
    }

    @Override
    public void setRoomType() {
        roomType.set("Double Room");
    }
}
