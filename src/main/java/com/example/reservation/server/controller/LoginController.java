package com.example.reservation.server.controller;

import com.example.reservation.server.model.Login;

import java.util.Hashtable;

public class LoginController {
    private Hashtable<String, String> credentials;

    public LoginController() {
        credentials = new Hashtable<>();
        credentials.put("admin123", "qwerty1234");
        credentials.put("hotelAdmin00", "pwd7890");
    }

    public boolean validateID(String loginID) {
        String val = credentials.get(loginID);

        if(val == null) {
            return false;
        }

        return true;
    }

    public boolean validateCredentials(String loginID, String password, Login loginCreds) {
        String passwordVal = credentials.get(loginID);

        if (!passwordVal.equals(password)) {
            return false;
        } else {
            loginCreds.setLoginID(loginID);
            loginCreds.setPassword(password);
            return true;
        }
    }
}
