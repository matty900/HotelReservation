package com.example.reservation.client.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.reservation.server.controller.ReservationController;
import com.example.reservation.server.model.Reservation;

public class DisplayBookings {
    public static void display(ObservableList<Reservation> bookingsList) {
        Stage bookingDisplayStage = new Stage();
        bookingDisplayStage.setHeight(550);
        bookingDisplayStage.setWidth(700);
        bookingDisplayStage.setTitle("Hotel Reservation - Room Booking");
        bookingDisplayStage.initModality(Modality.APPLICATION_MODAL);

        Label totalBookingsLabel = new Label("No. of current bookings: "+String.valueOf(bookingsList.size()));
        TableView<Reservation> reservationTableView = new TableView<>();
        reservationTableView.setEditable(false);

        TableColumn<Reservation, Integer> bookingIDCol = new TableColumn<>("Booking ID");
        bookingIDCol.setPrefWidth(100);
        bookingIDCol.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id"));

        TableColumn<Reservation, String> customerName = new TableColumn<>("Customer Name");
        customerName.setPrefWidth(100);
        customerName.setCellValueFactory(new PropertyValueFactory<Reservation, String>("guestName"));

        TableColumn<Reservation, String> roomTypeCol = new TableColumn<>("Room Type");
        roomTypeCol.setPrefWidth(100);
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<Reservation, String>("roomType"));

        TableColumn<Reservation, Integer> numRoomsCol = new TableColumn<>("No. of Rooms");
        numRoomsCol.setPrefWidth(120);
        numRoomsCol.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("numRooms"));

        TableColumn<Reservation, Integer> numDaysCol = new TableColumn<>("No. of days");
        numDaysCol.setPrefWidth(130);
        numDaysCol.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("numDays"));

        reservationTableView.setItems(bookingsList);
        reservationTableView.getColumns().addAll(bookingIDCol, customerName, roomTypeCol, numRoomsCol, numDaysCol);

        Button exitButton = new Button("Close");
        exitButton.setOnAction(actionEvent -> bookingDisplayStage.close());

        VBox mainContainer = new VBox(10);
        mainContainer.setPadding(new Insets(10, 0, 0,0));
        mainContainer.getChildren().addAll(totalBookingsLabel, reservationTableView, exitButton);
        mainContainer.setAlignment(Pos.CENTER);

        Scene scene=new Scene(mainContainer);
        bookingDisplayStage.setScene(scene);
        bookingDisplayStage.setResizable(false);
        bookingDisplayStage.showAndWait();
    }
}
