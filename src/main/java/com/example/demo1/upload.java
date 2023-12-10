package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static com.example.demo1.HelloApplication.connection;
public class upload {

    User currentUser = User.getCurrentUser();
    @FXML
    private TextField image_path_field;

    @FXML
    private TextField lesson_field;

    @FXML
    private TextField name_field;

    private Stage stage;
    private Scene scene;

    private Parent root;



    @FXML
    private void uploadImage(ActionEvent event) {
        String image_path = image_path_field.getText();
        String lesson = lesson_field.getText();
        String name= name_field.getText();
        if (image_path.isEmpty() || name.isEmpty()) {
            showAlert("Error", "Image path and name cannot be empty.");
            return;
        }

        else if(lesson.isEmpty())
        {
            uploadtoDatabase(image_path,  name);

        }

        else {
            uploadtoDatabase(image_path, lesson, name);


        }


    }

    private void uploadtoDatabase(String image_path, String name) {

        String sql = "INSERT INTO sign_symbols (image_path,  name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, image_path);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();

            showAlert("Success", "Image path and name uploaded successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to upload image path and lesson.");
        }


    }


    private void uploadtoDatabase(String image_path, String lesson, String name)
    {
        String sql = "INSERT INTO sign_symbols (image_path, lesson, name) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, image_path);
            preparedStatement.setString(2, lesson);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();

            showAlert("Lesson Upload Successful", "Image path,name  and lesson uploaded successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to upload image path and lesson.");
        }


    }



    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
}
