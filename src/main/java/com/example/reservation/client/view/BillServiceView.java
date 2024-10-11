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

import java.util.function.UnaryOperator;

public class BillServiceView
{
    public static void display(Client client) {
        Stage billStage = new Stage();
        billStage.setHeight(550);
        billStage.setWidth(700);
        billStage.setTitle("Hotel Reservation - Bill Service");
        billStage.initModality(Modality.APPLICATION_MODAL);

        Label titleLabel = new Label("Hotel Reservation - Enter Bill Service Details");
        titleLabel.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 15px");
        titleLabel.setPadding(new Insets(10));

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 9px");

        Label bookingIDLbl = new Label("Booking ID: ");
        Label nameLbl = new Label("Guest Name: ");
        Label numRoomsLbl = new Label("No of Rooms Booked: ");
        Label roomTypeLbl = new Label("Choose the room type: ");
        Label rateLbl = new Label("Rate per day: ");
        Label numDaysLbl = new Label("No of Days to be booked for: ");
        Label discountLbl = new Label("Discounts: %");
        Label totalLbl = new Label("Total Amount: ");

        TextField bookingIDField = new TextField();
        TextField nameField = new TextField();
        TextField numRoomsField = new TextField();
        String[] roomTypeItems = {"Single", "Double", "Deluxe", "Penthouse"};
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll(roomTypeItems);
        comboBox.getSelectionModel().selectFirst();
        TextField rateField = new TextField();
        TextField numDaysField = new TextField();
        TextField discountField = new TextField("0");
        TextField totalField = new TextField("$ ");
        totalField.setDisable(true);

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("\\d?")) { // this is the important line
                errorLabel.setText("");
                return change;
            }

            errorLabel.setText("*This field only accepts numeric values.");
            return null;
        };
        bookingIDField.setTextFormatter(new TextFormatter<String>(filter));
        numRoomsField.setTextFormatter(new TextFormatter<String>(filter));
        rateField.setTextFormatter(new TextFormatter<String>(filter));
        numDaysField.setTextFormatter(new TextFormatter<String>(filter));
        discountField.setTextFormatter(new TextFormatter<String>(filter));

        Button calcButton = new Button("Calculate");
        calcButton.setPrefWidth(160);
        calcButton.setOnAction(actionEvent -> {

            if(bookingIDField.getText().isEmpty() || nameField.getText().isEmpty() ||
                    numRoomsField.getText().isEmpty() || rateField.getText().isEmpty() ||
                    numDaysField.getText().isEmpty() || discountField.getText().isEmpty()) {
                errorLabel.setText("**Please fill all information before proceeding.");
            } else {
                errorLabel.setText("");

                String id = bookingIDField.getText();
                String numRooms = numRoomsField.getText();
                String rate = rateField.getText();
                String numOfDays = numDaysField.getText();
                String discount = discountField.getText();

                double total = Double.parseDouble(rate)*Double.parseDouble(numOfDays)
                        *Double.parseDouble(numRooms);
                double dis = Double.parseDouble(discount);

                if(dis > 0) {
                    total = total - (total*dis/100);
                }

                totalField.setText("$ "+String.valueOf(total));

                String data = "bill," +
                        id+","+
                        String.valueOf(total);

                client.sendMessage(data);

            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(160);
        exitButton.setOnAction(actionEvent -> billStage.close());

        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(calcButton, exitButton);
        buttonContainer.setPadding(new Insets(10));
        buttonContainer.setAlignment(Pos.CENTER);

        VBox labelsBox = new VBox(31);
        labelsBox.setPadding(new Insets(10));
        labelsBox.getChildren().addAll(bookingIDLbl, nameLbl, numRoomsLbl, roomTypeLbl, numDaysLbl, rateLbl, discountLbl, totalLbl);

        VBox fieldssBox = new VBox(23);
        fieldssBox.setPadding(new Insets(10));
        fieldssBox.getChildren().addAll(bookingIDField, nameField, numRoomsField, comboBox, numDaysField, rateField, discountField, totalField);

        HBox formContainer = new HBox(10);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.getChildren().addAll(labelsBox, fieldssBox);

        VBox mainContainer = new VBox(10);
        mainContainer.setPadding(new Insets(10, 0, 0,0));
        mainContainer.getChildren().addAll(titleLabel, formContainer, errorLabel, buttonContainer);
        mainContainer.setAlignment(Pos.CENTER);

        Scene scene=new Scene(mainContainer);
        billStage.setScene(scene);
        billStage.setResizable(false);
        billStage.showAndWait();
    }
}
