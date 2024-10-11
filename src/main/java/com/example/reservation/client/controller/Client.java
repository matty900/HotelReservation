package com.example.reservation.client.controller;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.reservation.client.view.AllRoomsView;
import com.example.reservation.client.view.ConfirmationView;
import com.example.reservation.client.view.DisplayBookings;
import com.example.reservation.server.model.Reservation;
import com.example.reservation.server.model.RoomSerializable;

public class Client {
    private static Client instance; // Singleton instance
    private Socket requestSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Client() {
        // Private constructor to prevent instantiation from outside
    }

    public static synchronized Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public void connect() throws IOException {
        requestSocket = new Socket("localhost", 5050);
        System.out.println("Connected to localhost on port 5050");
        out = new ObjectOutputStream(requestSocket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(requestSocket.getInputStream());
    }

    public void disconnect() {
        try {
            out.writeObject("exit");
            out.flush();

            if (in != null) in.close();
            if (out != null) out.close();

            if (requestSocket != null) {
                requestSocket.close();
            }
            System.out.println("Disconnected from server");
        } catch(EOFException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();

            try {
                Object serverMessage = in.readObject();

                if (serverMessage instanceof String) {
                    String message = (String) serverMessage;
                    System.out.println("Received message from server: " + message);
                    return message;
                } else if (serverMessage instanceof ArrayList<?>) {
                    List<Reservation> reservationList = (List<Reservation>) serverMessage;
                    ObservableList<Reservation> reservationsObservable = FXCollections.observableList(reservationList);
                    DisplayBookings.display(reservationsObservable);
                }else if (serverMessage instanceof Stack<?>) {
                    List<RoomSerializable> roomsList = (Stack<RoomSerializable>) serverMessage;
                    ObservableList<RoomSerializable> roomsObservable = FXCollections.observableList(roomsList);
                    AllRoomsView.display(roomsObservable);
                } else if(serverMessage instanceof Reservation) {
                    Reservation reservation = (Reservation) serverMessage;
                    ConfirmationView.display(reservation);
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}