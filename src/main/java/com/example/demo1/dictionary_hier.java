package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import javafx.scene.Parent;

import java.io.IOException;

public class dictionary_hier {


    User currentUser = User.getCurrentUser();


    @FXML
    Button alphabet1Button;

    @FXML
    Button numbersbutton;
    private Parent root;
    private Scene scene;

    private Stage stage;



    @FXML
    void onAlphabet1ButtonClick(ActionEvent event) throws IOException {

        switchtodictioanry_view(event, "Alphabet1");

    }

    @FXML
    void onAlphabet2ButtonClick(ActionEvent event) throws IOException {

        switchtodictioanry_view(event, "Alphabet2");

    }

    @FXML
    void ondaysButtonClick(ActionEvent event) throws IOException {

        switchtodictioanry_view(event, "Days");

    }

    @FXML
    void onMonthsButtonClick(ActionEvent event) throws IOException {

        switchtodictioanry_view(event, "Months");

    }

    @FXML
    void onTimeButtonClick(ActionEvent event) throws IOException {

        switchtodictioanry_view(event, "Time");

    }



    @FXML
    void onNumbersButtonClick(ActionEvent event) throws IOException{

        switchtodictioanry_view(event, "Months");

    }


    public void  switchtodictioanry_view(ActionEvent event, String lesson) throws IOException
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DictionaryView.fxml"));
        root = loader.load();
        Dictionary_controller controller = loader.getController();

        controller.loadDictionary(lesson);
        controller.show_Lesson();
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void  switchtodashboard(MouseEvent event) throws IOException
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
