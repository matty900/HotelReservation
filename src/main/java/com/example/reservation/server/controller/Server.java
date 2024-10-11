package com.example.reservation.server.controller;

import javafx.collections.ObservableList;
import com.example.reservation.server.model.Login;
import com.example.reservation.server.model.Reservation;
import com.example.reservation.server.model.Room;
import com.example.reservation.server.model.RoomSerializable;

import java.io.IOException;
import java.net.ServerSocket;
import java.io.*;
import java.net.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Server {

    ServerSocket providerSocket;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    private String message= null ;
    LoginController loginController;
    ReservationController reservationController = new ReservationController();;

    public Server() {}

    public String getMessage() {
        return message;
    }

    public void run() {
        try {
            //1. creating a server socket
            providerSocket = new ServerSocket(5050, 10);

            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            loginController = new LoginController();

            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            //3. get Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            System.out.println("connection success");

            //4. The two parts communicate via the input and output streams
            do {
                try {
                    String message = (String) in.readObject();
                    this.message = message;

                    if (message.equals("exit")) {
                        // Client has closed the connection
                        System.out.println("Client closed the connection.");
                    }

                    if (message.contains("login"))
                    {
                        String[] parts = message.split(",");
                        String email = parts[1];
                        String password = parts[2];

                        if(!loginController.validateID(email)) {
                            sendMessage("this_account_not_exists");
                        } else if (loginController.validateCredentials(email, password, new Login())){
                            System.out.println("correct_password");
                            sendMessage("correct_password");
                        }
                        else{
                            System.out.println("incorrect_password");
                            sendMessage("incorrect_password");
                        }
                        System.out.println("");
                    }

                    else if(message.contains("reservation")){
                        String[] parts = message.split(",");
                        String guestTitle = parts[1];
                        String guestFName = parts[2];
                        String guestLName = parts[3];
                        String guestAddress = parts[4];
                        int guestPhone = Integer.parseInt(parts[5]);
                        String guestEmail = parts[6];
                        int numGuests = Integer.parseInt(parts[7]);
                        String roomType = parts[8];
                        int numRooms = Integer.parseInt(parts[9]);
                        int numDays = Integer.parseInt(parts[10]);
                        double rate = Double.parseDouble(parts[11]);

                        reservationController.addBooking(guestTitle, guestFName, guestLName, guestAddress, guestPhone, guestEmail,
                                numGuests, roomType, numDays, rate, numRooms);
                        sendMessage("reservation done");
                    } else if (message.contains("getBooking")) {
                        String[] parts = message.split(",");
                        String email = parts[1];

                        Reservation reservation = reservationController.getReservation(email);
                        out.writeObject(reservation);
                        out.flush();
                        System.out.println("server> Sent Reservation");
                    } else if(message.contains("bookings")) {
                        try {
                            ObservableList<Reservation> bookings = reservationController.getAllBookingsList();
                            out.writeObject(new ArrayList<Reservation>(bookings));
                            out.flush();
                            sendMessage("bookings_sent");
                            System.out.println("server> Sent Bookings List");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    } else if(message.contains("bill")){
                        String[] parts = message.split(",");
                        int bID = Integer.parseInt(parts[1]);
                        double total = Double.parseDouble(parts[2]); // 034556
                        reservationController.addBill(bID, total);
                        sendMessage("bill_issued");
                    } else if(message.contains("rooms")) {
                        try {
                            ObservableList<Room> rooms = reservationController.getAllRooms();
                            List<RoomSerializable> allRooms = new Stack<RoomSerializable>();

                            for(Room room:rooms) {
                                allRooms.add(new RoomSerializable(room.getRoomID(), room.getRoomType(), room.getRoomRate()));
                            }
                            out.writeObject(allRooms);
                            out.flush();
                            sendMessage("all_rooms_sent");
                            System.out.println("server> Sent Available Rooms List");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }

                } catch (ClassNotFoundException classnot) {
                    System.err.println("Data received in unknown format");
                }
            } while(!this.message.equals("exit"));
        } catch (IOException ioException) {
            ioException.printStackTrace();

        }
        catch (NullPointerException ex){
            System.out.println(ex);
        }
        finally {
            //4: Closing connection
            try {
                in.close();
                out.close();
                providerSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("server> " + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}