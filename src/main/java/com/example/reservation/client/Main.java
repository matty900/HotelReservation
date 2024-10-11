package com.example.reservation.client;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.reservation.client.controller.Client;
import com.example.reservation.client.view.LoginView;
import com.example.reservation.client.view.UserMenu;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Button adminButton = new Button("Admin Login");
        Button selfButton = new Button("Self Booking");
        Label welcomeLbl = new Label("      Welcome \n\t   to\n    ABC Hotel\n");

        adminButton.setOnAction(event -> {
            primaryStage.close();
            try {
                LoginView.display();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        selfButton.setOnAction(actionEvent -> {
            primaryStage.close();
            try {
                UserMenu.display();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        primaryStage.setTitle("Hotel Reservation System");

        adminButton.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 13 ");
        selfButton.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 13 ");//setting font and size of button text

        welcomeLbl.setStyle("-fx-text-fill: blue;-fx-font-family: Calibri; -fx-font-weight: bold; -fx-font-size: 40;");
        VBox itemsBox = new VBox(70);

        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(adminButton, selfButton);
        buttonContainer.setAlignment(Pos.CENTER);

        itemsBox.getChildren().addAll(welcomeLbl, buttonContainer);
        itemsBox.setAlignment(Pos.CENTER);

        //Adding duplicate pane
        StackPane pane = new StackPane();
        pane.getChildren().add(itemsBox);
        Scene root = new Scene(pane, 400, 300);

        primaryStage.setScene(root);
        primaryStage.show();
    }
}
