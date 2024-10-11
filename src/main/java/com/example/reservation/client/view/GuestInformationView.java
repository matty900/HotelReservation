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
import com.example.reservation.client.model.Confirmation;
import com.example.reservation.client.model.ValidateEmail;

import java.util.Optional;
import java.util.function.UnaryOperator;

public class GuestInformationView {
    public static void display(Client client, String numOfGuests, String roomType, String numOfRooms, String numDays, String rate) {
        Stage guestInformationStage = new Stage();
        guestInformationStage.setHeight(550);
        guestInformationStage.setWidth(700);
        guestInformationStage.setTitle("Hotel Reservation - Guest Information");
        guestInformationStage.initModality(Modality.APPLICATION_MODAL);

        Label titleLabel = new Label("Hotel Reservation - Enter Guest Information");
        titleLabel.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 15px");
        titleLabel.setPadding(new Insets(10));

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 9px");

        Label guestTitleLbl = new Label("*Your Title: ");
        Label firstNameLbl = new Label("*First Name: ");
        Label lastNameLbl = new Label("*Last Name: ");
        Label addressLabel = new Label("*Address: ");
        Label phoneLabel = new Label("*Phone: ");
        Label emailLabel = new Label("*Email: ");

        String[] guestTitleItems = {"Mr", "Ms", "Mrs"};
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll(guestTitleItems);
        comboBox.getSelectionModel().selectFirst();

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("^[a-zA-Z]*$")) { // this is the important line
                errorLabel.setText("");
                return change;
            }

            errorLabel.setText("*This field cannot accept numeric values.");
            return null;
        };

        TextField firstNameField = new TextField();
        firstNameField.setTextFormatter(new TextFormatter<String>(filter));
        TextField lastNameField = new TextField();
        lastNameField.setTextFormatter(new TextFormatter<String>(filter));
        TextField addressField = new TextField();
        TextField phoneField = new TextField();
        UnaryOperator<TextFormatter.Change> phoneFilter = change -> {
            String text = change.getText();

            if (text.matches("\\d?")) { // this is the important line
                errorLabel.setText("");
                return change;
            }

            errorLabel.setText("*This field only accepts numeric values.");
            return null;
        };
        phoneField.setTextFormatter(new TextFormatter<String>(phoneFilter));

        TextField emailField = new TextField();
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!ValidateEmail.checkEmail(emailField.getText())) {
                errorLabel.setText("*Enter a valid email: someone@email.com");
            } else {
                errorLabel.setText("");
            }
        });

        Button confirmBookingButton = new Button("Confirm Booking");
        confirmBookingButton.setPrefWidth(160);
        confirmBookingButton.setOnAction(actionEvent -> {

            if(firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                    addressField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                    emailField.getText().isEmpty() || !ValidateEmail.checkEmail(emailField.getText())
            ) {
                errorLabel.setText("**Please fill all information before proceeding.");
            } else {
                String title = comboBox.getValue().toString();
                String fName = firstNameField.getText();
                String lName = lastNameField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                errorLabel.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm");
                alert.setHeaderText("Confirm booking for " + title + ". "+ fName + " "+ lName+ "?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String reservationData = "reservation,"
                            +title+","
                            +fName+","
                            +lName+","
                            +address+","
                            +phone+","
                            +email+","
                            +numOfGuests+","
                            +roomType+","
                            +numOfRooms+","
                            +numDays+","
                            +rate;
                    client.sendMessage(reservationData);
                    client.sendMessage("getBooking,"+email);

                    guestInformationStage.close();
                }
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(160);
        exitButton.setOnAction(actionEvent -> guestInformationStage.close());

        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(confirmBookingButton, exitButton);
        buttonContainer.setPadding(new Insets(10));
        buttonContainer.setAlignment(Pos.CENTER);

        VBox labelsBox = new VBox(31);
        labelsBox.setPadding(new Insets(10));
        labelsBox.getChildren().addAll(guestTitleLbl, firstNameLbl, lastNameLbl, addressLabel, phoneLabel, emailLabel);

        VBox fieldssBox = new VBox(23);
        fieldssBox.setPadding(new Insets(10));
        fieldssBox.getChildren().addAll(comboBox, firstNameField, lastNameField, addressField, phoneField, emailField);

        HBox formContainer = new HBox(10);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.getChildren().addAll(labelsBox, fieldssBox);

        VBox mainContainer = new VBox(10);
        mainContainer.setPadding(new Insets(10, 0, 0,0));
        mainContainer.getChildren().addAll(titleLabel, formContainer, errorLabel, buttonContainer);
        mainContainer.setAlignment(Pos.CENTER);

        Scene scene=new Scene(mainContainer);
        guestInformationStage.setScene(scene);
        guestInformationStage.setResizable(false);
        guestInformationStage.showAndWait();
    }
}
