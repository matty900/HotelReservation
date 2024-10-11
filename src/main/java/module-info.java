module com.example.reservation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;



    opens com.example.reservation to javafx.fxml;
    opens com.example.reservation.server.model to javafx.base;
    exports com.example.reservation;
    exports com.example.reservation.client;
}