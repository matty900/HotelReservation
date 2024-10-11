package com.example.reservation.server.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Hashtable;

public class Login {
    private StringProperty loginID;
    private StringProperty password;

    public Login() {
        loginID = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }


    public String getLoginID() {
        return loginID.get();
    }

    public StringProperty loginIDProperty() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID.set(loginID);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
