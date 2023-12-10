package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class SearchBarController {
    User currentUser = User.getCurrentUser();

    private Stage stage;
    private Scene scene;

    private Parent root;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView signImage;

    private final String DB_URL = "jdbc:mysql://localhost:3306/signify";
    private final String USERNAME = "root";
    private final String PASSWORD = "v=ltX5AtW9v30";


    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchSign();

        }
    }

    public void searchSign() {
        String userInput = searchField.getText();

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM sign_symbols WHERE name=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userInput);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               String imagePath = resultSet.getString("image_path");
                Image image = new Image("file:" + imagePath);
                ImageView imageView = new ImageView(image);

                Stage newStage = new Stage();
                newStage.setTitle("Sign Language Symbol");
                newStage.setScene(new Scene(new StackPane(imageView), 400, 400));
                newStage.show();



            } else {
                signImage.setImage(null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ForA(){

        String imagepath="D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\alphabets\\Alphabets\\A.png";
      Suggested(imagepath);
    }




    public void ForB() {
        String imagepath = "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\alphabets\\Alphabets\\B.png";
        Suggested(imagepath);

    }
    public void ForC() {
        String imagepath = "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\alphabets\\Alphabets\\C.png";
        Suggested(imagepath);

    }
    public void Nicetomeetyou() {
        String imagepath = "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\Images\\nicetomeetyou.gif";
        Suggested(imagepath);

    }
    public void Goodmorning() {
        String imagepath = "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\Images\\Goodmorning.gif";
        Suggested(imagepath);

    }




    public void Suggested(String imagepath)
    {
        Image image = new Image("file:" + imagepath);
        ImageView imageView = new ImageView(image);

        Stage newStage = new Stage();
        newStage.setTitle("Sign Language Symbol");
        newStage.setScene(new Scene(new StackPane(imageView), 400, 400));
        newStage.show();
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
