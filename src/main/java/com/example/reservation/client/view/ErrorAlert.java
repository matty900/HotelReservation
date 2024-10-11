package com.example.reservation.client.view;

import javafx.scene.control.Alert;

public class ErrorAlert {
    public static void display(String errorType, String errorMessage)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(errorType);
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

}