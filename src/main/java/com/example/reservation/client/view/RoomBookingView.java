package com.example.reservation.client.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.reservation.client.controller.Client;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;

public class RoomBookingView {
    public static void display(Client client) {

        AtomicInteger numRooms = new AtomicInteger();

        Stage roomBookStage = new Stage();
        roomBookStage.setHeight(550);
        roomBookStage.setWidth(700);
        roomBookStage.setTitle("Hotel Reservation - Room Booking");
        roomBookStage.initModality(Modality.APPLICATION_MODAL);

        Label titleLabel = new Label("Hotel Reservation - Book A Room");
        titleLabel.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 15px");
        titleLabel.setPadding(new Insets(10));

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 9px");

        Label guestsLbl = new Label("No of Guests: ");
        Label availableLbl = new Label("Rooms Available: ");
        Label roomTypeLbl = new Label("Choose the room type: ");
        Label numDaysLbl = new Label("No of Days to be booked for: ");
        Label rateLbl = new Label("Rate per day: ");

        TextField guestField = new TextField();
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("\\d?")) { // this is the important line
                errorLabel.setText("");
                return change;
            }

            errorLabel.setText("*This field only accepts numeric values.");
            return null;
        };
        guestField.setTextFormatter(new TextFormatter<String>(filter));

        TextField availableField = new TextField();
        availableField.setDisable(true);

        TextField numDaysField = new TextField();
        numDaysField.setTextFormatter(new TextFormatter<String>(filter));

        TextField rateField = new TextField();
        rateField.setTextFormatter(new TextFormatter<String>(filter));

        String[] roomTypeItems = {"Single", "Double", "Deluxe", "Penthouse"};
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll(roomTypeItems);
        comboBox.getSelectionModel().selectFirst();

        comboBox.setOnAction(event -> {
            String roomType = comboBox.getValue().toString();
            String guestStr = guestField.getText();

            if(!guestStr.isEmpty() && !roomType.isEmpty())
            {
                int numOfGuests = Integer.parseInt(guestStr);
                if(roomType.equals("Single")) {
                    numRooms.set(getNumberOfSingleRooms(numOfGuests, availableField));
                } else if (roomType.equals("Double")) {
                    numRooms.set(getNumberOfDoubleRooms(numOfGuests, availableField));
                } else if (roomType.equals("Deluxe")) {
                    int numOfRooms = (int) Math.ceil((double) numOfGuests / 1);
                    availableField.setText(String.valueOf(numOfRooms)+ " Deluxe Room(s)");
                    numRooms.set(numOfRooms);
                } else {
                    availableField.setText("1 Penthouse");
                    numRooms.set(1);
                }
            }
        });

        Button nextButton = new Button("Next");
        nextButton.setPrefWidth(160);
        nextButton.setOnAction(actionEvent -> {

            if(guestField.getText().isEmpty() || rateField.getText().isEmpty() ||
                    numDaysField.getText().isEmpty()) {
                errorLabel.setText("**Please fill all information before proceeding.");
            } else {
                errorLabel.setText("");

                String numGuests = guestField.getText();
                String rate = rateField.getText();
                String roomType = comboBox.getValue().toString();
                String numOfDays = numDaysField.getText();

                GuestInformationView.display(client, numGuests, roomType, String.valueOf(numRooms.get()), numOfDays, rate);
                guestField.setText("");
                rateField.setText("");
                availableField.setText("");
                numDaysField.setText("");
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(160);
        exitButton.setOnAction(actionEvent -> roomBookStage.close());

        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(nextButton, exitButton);
        buttonContainer.setPadding(new Insets(10));
        buttonContainer.setAlignment(Pos.CENTER);

        VBox labelsBox = new VBox(31);
        labelsBox.setPadding(new Insets(10));
        labelsBox.getChildren().addAll(guestsLbl, roomTypeLbl, availableLbl, numDaysLbl, rateLbl);

        VBox fieldssBox = new VBox(23);
        fieldssBox.setPadding(new Insets(10));
        fieldssBox.getChildren().addAll(guestField, comboBox, availableField, numDaysField, rateField);

        HBox formContainer = new HBox(10);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.getChildren().addAll(labelsBox, fieldssBox);

        VBox mainContainer = new VBox(10);
        mainContainer.setPadding(new Insets(10, 0, 0,0));
        mainContainer.getChildren().addAll(titleLabel, formContainer, errorLabel, buttonContainer);
        mainContainer.setAlignment(Pos.CENTER);

        Scene scene=new Scene(mainContainer);
        roomBookStage.setScene(scene);
        roomBookStage.setResizable(false);
        roomBookStage.showAndWait();
    }

    static int getNumberOfDoubleRooms(int numOfGuests, TextField availableField) {
        if (numOfGuests <= 2) {
            // Up to 2 adults share a double room
            availableField.setText("1 Double Guest Room");
            return 1;
        } else if (numOfGuests <= 4) {
            // 3 or 4 adults can have a double room or two single rooms
            availableField.setText("2 Double Guest Room(s), OR \n 1 Double, 1 Single Guest Room");
            return 2;
        } else {
            // More than 4 adults will have multiple double or combination of double and single rooms
            int numOfDoubleRooms = (int) Math.ceil((double) numOfGuests / 2);
            int numOfSingleRooms = numOfGuests % 2;
            availableField.setText(String.valueOf(numOfDoubleRooms)+" Double Guest Room(s), " + String.valueOf(numOfSingleRooms)+ " Single Guest Room(s)");
            return numOfSingleRooms+numOfDoubleRooms;
        }
    }

    static int getNumberOfSingleRooms(int numOfGuests, TextField availableField)
    {
        if(numOfGuests <= 2) {
            availableField.setText("1 Single Guest Room(s)");
            return 1;
        } else {
            int numOfRooms = (int) Math.ceil((double) numOfGuests / 2);
            availableField.setText(String.valueOf(numOfRooms)+ " Single Guest Room(s)");
            return numOfRooms;
        }
    }
}
