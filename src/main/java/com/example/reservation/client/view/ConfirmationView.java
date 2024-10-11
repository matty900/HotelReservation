package com.example.reservation.client.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.reservation.server.model.Reservation;

import java.time.format.DateTimeFormatter;

public class ConfirmationView {
    public static void display(Reservation reservation) {

        Stage confirmationStage=new Stage();

        //setting its properties
        confirmationStage.setTitle("Reservation Confirmation");
        confirmationStage.setHeight(450);
        confirmationStage.setWidth(350);
        confirmationStage.initModality(Modality.APPLICATION_MODAL);

        //adding labels
        Label summaryLabel=new Label();
        summaryLabel.setPrefSize(300, 600);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //setting the text in lbl_Receipt
        summaryLabel.setText("\t\tHotel Reservation System\n\n" +"\n\n*******************************************\n" +
                "Reservation ID:        "+ reservation.getId()+
                " \nGuest Name:           "+ reservation.getGuestName()+
                "\n\n*******************************************\n"+
                " \nNo. of Guests:      "+ reservation.getNumGuests()+
                " \nNo. of Rooms:       "+ reservation.getNumRooms()+
                " \nRoom Type:          "+ reservation.getRoomType()+
                " \nRate per Day:       $"+ reservation.getRate()+
                " \nCheckin Date:       "+ reservation.getCheckinDate().format(formatter)+
                " \nCheckout Date:      "+ reservation.getCheckoutDate().format(formatter)+
                "\n\n*******************************************\n"+
                " \n  Thank you for choosing us for your stay! "+
                "\n\n*******************************************\n"
        );

        Button closeButton = new Button("Close");
        closeButton.setOnAction(actionEvent -> confirmationStage.close());

        VBox vb=new VBox(10);
        vb.getChildren().addAll(summaryLabel, closeButton);
        vb.setPadding(new Insets(10));
        vb.setStyle("-fx-background-color: white");
        vb.setAlignment(Pos.CENTER);

        Scene scene=new Scene(vb);
        confirmationStage.setScene(scene);
        confirmationStage.showAndWait();
    }
}
