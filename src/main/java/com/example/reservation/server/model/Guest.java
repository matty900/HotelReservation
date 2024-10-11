package com.example.reservation.server.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Guest
{
    private IntegerProperty id;
    private StringProperty title;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty address;
    private IntegerProperty phone;
    private StringProperty email;

    public Guest(String title, String fName, String lName, String address, int phone, String email) {
        this.id = new SimpleIntegerProperty(0);
        this.title = new SimpleStringProperty(title);
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleIntegerProperty(phone);
        this.email = new SimpleStringProperty(email);
    }


    public int getId() {
        return id.get();
    }

    public void setId(int id){
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public int getPhone() {
        return phone.get();
    }

    public IntegerProperty phoneProperty() {
        return phone;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }
}
