package com.example.demo1;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo1.HelloApplication.connection;

public abstract class User {

    private static User currentUser;

    private String username;
    private String email;
    private String password;
    private int totalQuestions;

    private String dob;

    List<Lesson> completedLessons;

    public User(String username, String dob, String email) {
        this.username = username;
        this.email = email;
        this.dob = dob;


    }



    public User(String username, String dob, String password, String email, int totalQuestions, int correctAnswers) {


        this.username = username;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.totalQuestions = totalQuestions;
        this.completedLessons = new ArrayList<>();
    }

    public abstract String getUserType();



    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setTotalQuestion(int totalQuestions) {
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE users SET totalQuestions = ? WHERE username = ?")) {
            preparedStatement.setInt(1, totalQuestions);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.totalQuestions = totalQuestions;
    }



    public List<Lesson> getCompletedLessons() {
        return completedLessons;
    }




    public int getTotalQuestions() {
        return totalQuestions;
    }
}
