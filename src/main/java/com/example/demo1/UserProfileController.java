package com.example.demo1;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.example.demo1.HelloApplication.connection;



public class UserProfileController {

    User currentUser = User.getCurrentUser();

    private Stage stage;
    private Scene scene;

    private Parent root;


    @FXML
    private Label studentNameLabel;

    @FXML
    private Label dateOfBirthLabel;

    @FXML
    private Label userType;

    @FXML
    private Label emailLabel;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Label mobileNumberLabel;

    public void initialize() {
        try {
            String username = currentUser.getUsername();
            String query = "SELECT * FROM users WHERE username = ?";
            System.out.println(username);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String studentName = resultSet.getString("username");
                String email = resultSet.getString("email");
                String fullName = resultSet.getString("full_name");
                String dob = resultSet.getString("dob");


                if(studentNameLabel!=null) studentNameLabel.setText(studentName);
                userType.setText(currentUser.getUserType());
                emailLabel.setText(email);
                fullNameLabel.setText(fullName);
               dateOfBirthLabel.setText(dob);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void switchtodashboard(MouseEvent event) throws IOException
    {
        String a;
        if(currentUser.getUserType()=="Learner") {
            a="dashboard.fxml";
        }
        else a="educator_dashboard.fxml";

        root = FXMLLoader.load(getClass().getResource(a));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void switchtoEditProfile(MouseEvent event)  throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("EditProfile.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
