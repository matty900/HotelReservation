package com.example.reservation;

import com.example.reservation.server.controller.Server;

import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {
        Server server = new Server();

        while(true) {
            server.run();
        }
    }
}
