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

import java.io.IOException;

public class UserMenu {
    public static void display() throws IOException {
        Client client = Client.getInstance();
        client.connect();

        Stage userMenuStage = new Stage();
        userMenuStage.setHeight(300);
        userMenuStage.setWidth(400);
        userMenuStage.setTitle("Hotel Reservation Menu");
        userMenuStage.initModality(Modality.APPLICATION_MODAL);

        Label titleLabel = new Label("User Menu");
        titleLabel.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 15px");
        titleLabel.setPadding(new Insets(10));

        Button bookButton = new Button("Book A Room");
        bookButton.setPrefWidth(250);
        bookButton.setStyle("-fx-font-weight: bold; -fx-font-size: 12px");
        bookButton.setOnAction(actionEvent -> {
            RoomBookingView.display(client);
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
            userMenuStage.close();
        });

        VBox buttonContainer = new VBox(10);
        buttonContainer.getChildren().addAll(titleLabel, bookButton, availableRoomsButton, exitButton);

        buttonContainer.setPadding(new Insets(10));
        buttonContainer.setAlignment(Pos.CENTER);

        Scene scene=new Scene(buttonContainer);
        userMenuStage.setScene(scene);
        userMenuStage.setResizable(false);
        userMenuStage.showAndWait();
    }
}
