package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

import static com.example.demo1.HelloApplication.connection;

public class login {
    @FXML
    private Button button_log_in;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private HashMap<String, String> logininfo;

    public login() {
        logininfo = IDandPasswords.logininfo;
    }

    public void switchtosignup(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SignUp1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchtoforgetpass(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("forget_pass.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchtodashboard(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        //Connection connection = sql_connection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username='" + username + "' AND password='" + password +"';");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String usname = resultSet.getString("username");
                String user_type = resultSet.getString("user_type");
                String email = resultSet.getString("email");
                String dob= resultSet.getString("dob");
                int totalQuestions = resultSet.getInt("totalQuestions");
                int correctAnswers = resultSet.getInt("correctAnswers");
                User user;
                if(user_type.equals("Educator")){

                    user = new Educator(username, dob, email);
                    User.setCurrentUser(user);
                    root = FXMLLoader.load(getClass().getResource("educator_dashboard.fxml"));


                }
                else{
                    user = new Learner(username, dob, password, email, totalQuestions, correctAnswers);
                    User.setCurrentUser(user);
                    root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    System.out.println(user.getUsername());
                }


                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password. Please try again.");
                alert.showAndWait();
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}