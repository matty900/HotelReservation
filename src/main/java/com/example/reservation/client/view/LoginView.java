package com.example.reservation.client.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.reservation.client.controller.Client;

import java.io.IOException;

public class LoginView
{
    public static void display() throws IOException {
        Client client = Client.getInstance();
        client.connect();

        Stage loginStage = new Stage();

        Button loginButton = new Button("Login");
        Label welcomeLbl = new Label("Login to Proceed\n"); // setting the label for page
        Label userEmailLbl = new Label("Enter User Email\n");
        Label passwordLbl = new Label("Enter Password\n");
        TextField userEmailField = new TextField();
        TextField userPasswordField = new TextField();

        loginStage.setTitle("Hotel Reservation System"); //setting title of the stage

        loginButton.setStyle("-fx-font-weight: bold; -fx-font-size: 15 ");//setting font and size of button text
        loginButton.setOnAction(actionEvent -> {
            String loginData = "login,"
                    + userEmailField.getText()+","
                    + userPasswordField.getText();
            String response = client.sendMessage(loginData);

            if(response.equals("this_account_not_exists")) {
                ErrorAlert.display("Account not found", "The account ID does not exist. Please try again");
            } else if (response.equals("incorrect_password")) {
                ErrorAlert.display("Incorrect Password", "The password you have entered is incorrect. Please try again");
            } else {
                loginStage.close();
                ReservationMenu.display(client);
            }
        });

        welcomeLbl.setStyle("--fx-font-family: Calibri; -fx-font-weight: bold; -fx-font-size: 40;");
        VBox itemsBox = new VBox(); //using Vbox to set all nodes in a single column

        VBox mainBox = new VBox(10);
        HBox emailBox = new HBox();
        HBox passwordBox = new HBox();

        emailBox.setPadding(new Insets(10));
        emailBox.setSpacing(10);

        passwordBox.setPadding(new Insets(10));
        passwordBox.setSpacing(10);

        emailBox.getChildren().addAll(userEmailLbl, userEmailField);
        passwordBox.getChildren().addAll(passwordLbl, userPasswordField);

        mainBox.setPadding(new Insets(55));
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().addAll(emailBox, passwordBox);

        itemsBox.getChildren().addAll(welcomeLbl, mainBox, loginButton); //adding label and button to the Vbox
        itemsBox.setAlignment(Pos.CENTER); //align vbox items to center

        //Adding duplicate pane
        StackPane pane = new StackPane();
        pane.getChildren().add(itemsBox);
        Scene root = new Scene(pane, 400, 400);

        loginStage.setScene(root);
        loginStage.show();
    }
}
