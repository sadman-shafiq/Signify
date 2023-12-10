package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.example.demo1.HelloApplication.connection;
public class Dictionary_controller {


    User currentUser = User.getCurrentUser();
    @FXML
    private ImageView signImageView = new ImageView();;

    @FXML
    private ImageView lessonCompletedImageView=new ImageView();

    @FXML
    private Label signNameLabel= new Label();
    @FXML
    private Button nextButton;

    private List<Sign> dictionary;

    private int currentQuestionIndex;



    @FXML
    private Button alphabet1Button;
    @FXML
    private Button numbersButton;
    private Parent root;
    private Stage stage;
    private Scene scene;




    public void loadDictionary(String selectedLesson) {
        dictionary = new ArrayList<>();

        try (
             Statement statement = connection .createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * FROM sign_symbols WHERE lesson = '" + selectedLesson + "'")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String imagePath = resultSet.getString("image_path");
                String signName = resultSet.getString("name");

                Sign sign = new Sign(id, imagePath, signName);
                dictionary.add(sign);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void show_Lesson() {
        if (currentQuestionIndex < dictionary.size()) {
            Sign currentQuestion = dictionary.get(currentQuestionIndex);
            Image signImage = new Image(currentQuestion.getImagePath());
            System.out.println(currentQuestion.getImagePath());
            signImageView.setImage(signImage);
            signNameLabel.setText(currentQuestion.getSignName());
            currentUser.setTotalQuestion(currentQuestionIndex + 1);
        } else {



           // nextButton.setText("Continue");
            lessonCompletedImageView.setImage(new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\Styles\\lesson_complete.png"));


        }
    }

    public void nextQuestion() {
        if (currentQuestionIndex < dictionary.size()  ) {
            currentQuestionIndex++;
            show_Lesson();
        } else {
            try {
                String a;
                if(currentUser.getUserType()=="Learner") {
                    a="dashboard.fxml";
                }
                else a="educator_dashboard.fxml";

                FXMLLoader loader = new FXMLLoader(getClass().getResource(a));
                Parent root = loader.load();

                //DashboardController dashboardController = loader.getController();

                Stage stage = (Stage) nextButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Sign {
        private final int id;
        private final String imagePath;
        private final String signName;

        public Sign(int id, String imagePath, String signName) {
            this.id = id;
            this.imagePath = imagePath;
            this.signName = signName;
        }

        public int getId() {
            return id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getSignName() {
            return signName;
        }
    }

    public void switch_lesson(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("lessons.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        // stage.setFullScreen(true);
        stage.show();
    }

    public void  switchtodashboard(ActionEvent event) throws IOException
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
        // stage.setFullScreen(true);
        stage.show();

    }


    public void  switchtodictioanry_view(ActionEvent event) throws IOException
    {

        root = FXMLLoader.load(getClass().getResource("DictionaryView.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        // stage.setFullScreen(true);
        stage.show();


    }


    public void  switchtohier(MouseEvent event) throws IOException
    {


        root = FXMLLoader.load(getClass().getResource("dictionary_hier.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        // stage.setFullScreen(true);
        stage.show();

    }

}
