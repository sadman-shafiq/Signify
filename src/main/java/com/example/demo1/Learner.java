package com.example.demo1;

public class Learner extends User implements MembershipType{

    private String membership;

    public Learner(String username, String dob, String email) {
        super(username, dob, email);
    }




    public Learner(String username, String dob, String password, String email, int totalQuestions, int correctAnswers) {
                            super(username, dob, password, email, totalQuestions, correctAnswers);
    }

    @Override
    public String getUserType() {
        return "Learner";
    }

    @Override
    public String getMembership() {
        
        return membership;
    }

    @Override
    public void setMembership(String membership) {
        this.membership = membership;

    }
}
