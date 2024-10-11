package com.example.reservation.server.model;
public class SingleRoom extends Room {

    public SingleRoom(int id, double rate) {
        roomID.set(id);
        roomRate.set(rate);
        setRoomType();
    }

    @Override
    public void setRoomType() {
        roomType.set("Single Room");
    }
}
