package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signup {

    @FXML
    private TextField full_namefield;

    @FXML
    private TextField usernamefield;

    @FXML
    private TextField email_field;
    @FXML
    private DatePicker dobField;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private RadioButton educatorRadioButton;




    @FXML
    private RadioButton learnerRadioButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void accountcreated(ActionEvent event) throws IOException {

        String username = usernamefield.getText();
        String full_name = full_namefield.getText();

        String dob = "";
        if (dobField.getValue() != null) {
            dob = dobField.getValue().toString();
        }
        String password = passwordfield.getText();

        String email = email_field.getText();
        String userType = educatorRadioButton.isSelected() ? "Educator" : "Learner";
        Connection connection = sql_connection.getConnection();


        try{
            if(!email.contains("@")){
                throw new InvalidEmailException("Email doesn't contain @");
            }

            try {


                PreparedStatement checkUserexists= connection.prepareStatement("SELECT * FROM users WHERE username=?");
                checkUserexists.setString(1, username);

                ResultSet resultSet = checkUserexists.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Sign Up Failed! Username already exists. ");
                    alert.showAndWait();


                } else {
                    System.out.println(password);
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password, progress, user_type, email, dob, full_name) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    preparedStatement.setInt(3, 0);
                    preparedStatement.setString(4, userType);
                    preparedStatement.setString(5, email);
                    preparedStatement.setString(6, dob);
                    preparedStatement.setString(7, full_name);
                    preparedStatement.executeUpdate();




                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sign Up");
                    alert.setHeaderText(null);
                    alert.setContentText("Sign Up Successful!");
                    alert.showAndWait();



                    User user;
                    if (userType.equals("Educator")) {
                        user = new Educator(username, dob, password);
                        User.setCurrentUser(user);
                        root = FXMLLoader.load(getClass().getResource("educator_dashboard.fxml"));
                    } else {
                        user = new Learner(username, dob, password);
                        User.setCurrentUser(user);
                        root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    }
                    User.setCurrentUser(user);
                    //root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                   // stage.setFullScreen(true);
                    stage.show();
                    preparedStatement.close();

                }


            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        catch(InvalidEmailException e){

            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Email address");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }



         finally {
           // sql_connection.closeConnection();
        }
    }

    public void switchtologin(MouseEvent event) throws IOException
    {

        root = FXMLLoader.load(getClass().getResource("Login page1"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
