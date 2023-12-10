package com.example.demo1;


public class Educator extends User {

    public Educator(String username, String dob, String email) {
        super(username, dob, email);
    }

    @Override
    public String getUserType() {
        return "Educator";
    }


}
