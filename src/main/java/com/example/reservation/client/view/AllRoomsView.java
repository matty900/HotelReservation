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
import com.example.reservation.server.model.RoomSerializable;


public class AllRoomsView {

    public static void display(ObservableList<RoomSerializable> allRooms) {
        Stage roomDisplayStage = new Stage();
        roomDisplayStage.setHeight(550);
        roomDisplayStage.setWidth(600);
        roomDisplayStage.setTitle("Hotel Reservation - Available Rooms");
        roomDisplayStage.initModality(Modality.APPLICATION_MODAL);

        Label roomsAvailable = new Label("No. of current available rooms: "+ String.valueOf(allRooms.size()));
        TableView<RoomSerializable> roomsTableView = new TableView<>();
        roomsTableView.setEditable(false);

        TableColumn<RoomSerializable, Integer> roomIDCol = new TableColumn<>("Room ID");
        roomIDCol.setPrefWidth(170);
        roomIDCol.setCellValueFactory(new PropertyValueFactory<RoomSerializable, Integer>("roomID"));

        TableColumn<RoomSerializable, String> roomTypeCol = new TableColumn<>("Room Type");
        roomTypeCol.setPrefWidth(170);
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<RoomSerializable, String>("roomType"));

        TableColumn<RoomSerializable, Double> rateCol = new TableColumn<>("Rate per day");
        rateCol.setPrefWidth(170);
        rateCol.setCellValueFactory(new PropertyValueFactory<RoomSerializable, Double>("rate"));

        roomsTableView.setItems(allRooms);
        roomsTableView.getColumns().addAll(roomIDCol, roomTypeCol, rateCol);

        Button exitButton = new Button("Close");
        exitButton.setOnAction(actionEvent -> roomDisplayStage.close());

        VBox mainContainer = new VBox(10);
        mainContainer.setPadding(new Insets(10, 0, 0,0));
        mainContainer.getChildren().addAll(roomsAvailable, roomsTableView, exitButton);
        mainContainer.setAlignment(Pos.CENTER);

        Scene scene=new Scene(mainContainer);
        roomDisplayStage.setScene(scene);
        roomDisplayStage.setResizable(false);
        roomDisplayStage.showAndWait();
    }

}
