package com.example.demo1;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.demo1.HelloApplication.connection;


public class EditProfile {


    User currentUser = User.getCurrentUser();

    private Stage stage;
    private Scene scene;

    private  Parent root;



    @FXML
    private TextField full_name_textfield;
    @FXML
    private TextField email_field;
    @FXML
    private TextField dob_field;

    @FXML
    private void update_fullname(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            updateField("full_name", full_name_textfield.getText());



        }
    }

    @FXML
    private void update_email(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            updateField("email", email_field.getText());



        }
    }


    @FXML
    private void update_dob(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            updateField("dob", dob_field.getText());



        }
    }





    private void updateField(String fieldName, String newValue) {
        try {
            String username = currentUser.getUsername();
            String updateQuery = "UPDATE users SET " + fieldName + " = ? WHERE username = ?";

            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

            updateStatement.setString(1, newValue);
            updateStatement.setString(2, username);

            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Profile updated successfully!");
            } else {
                System.out.println("Failed to update profile. No matching user found.");
            }

            updateStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void switchtoUserProfile(MouseEvent event)  throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("StudentUser.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
