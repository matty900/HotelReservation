package com.example.reservation.client.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.reservation.client.controller.Client;


public class ReservationMenu {
    public static void display(Client client) {
        Stage menuStage = new Stage();
        menuStage.setHeight(300);
        menuStage.setWidth(400);
        menuStage.setTitle("Hotel Reservation Menu");
        menuStage.initModality(Modality.APPLICATION_MODAL);

        Label titleLabel = new Label("Admin Menu");
        titleLabel.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 15px");
        titleLabel.setPadding(new Insets(10));

        Button bookButton = new Button("Book A Room");
        bookButton.setPrefWidth(250);
        bookButton.setStyle("-fx-font-weight: bold; -fx-font-size: 12px");
        bookButton.setOnAction(actionEvent -> {
            RoomBookingView.display(client);
        });

        Button billButton = new Button("Bill Service");
        billButton.setPrefWidth(250);
        billButton.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
        billButton.setOnAction(actionEvent -> BillServiceView.display(client));

        Button currentBookingsButton = new Button("Current Bookings");
        currentBookingsButton.setPrefWidth(250);
        currentBookingsButton.setStyle("-fx-font-weight: bold; -fx-font-size: 12px");
        currentBookingsButton.setOnAction(actionEvent -> {
            client.sendMessage("bookings");
        });

        Button availableRoomsButton = new Button("Available Rooms");
        availableRoomsButton.setPrefWidth(250);
        availableRoomsButton.setStyle("-fx-font-weight: bold; -fx-font-size: 12px");
        availableRoomsButton.setOnAction(actionEvent -> {
            client.sendMessage("rooms");
        });

        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(250);
        exitButton.setStyle("-fx-font-weight: bold; -fx-font-size: 12px");
        exitButton.setOnAction(actionEvent -> {
            client.disconnect();
            menuStage.close();
        });

        VBox buttonContainer = new VBox(10);
        buttonContainer.getChildren().addAll(titleLabel, bookButton, billButton, currentBookingsButton, availableRoomsButton, exitButton);

        buttonContainer.setPadding(new Insets(10));
        buttonContainer.setAlignment(Pos.CENTER);

        Scene scene=new Scene(buttonContainer);
        menuStage.setScene(scene);
        menuStage.setResizable(false);
        menuStage.showAndWait();
    }
}
