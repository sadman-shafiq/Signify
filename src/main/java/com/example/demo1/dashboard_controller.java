/*package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}

 */

package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class dashboard_controller {

    User currentUser = User.getCurrentUser();

    @FXML
    private Label usernameLabel;
    private Stage stage;
    private Scene scene;

    private  Parent root;


    public void initialize( ) {

        if (usernameLabel != null) usernameLabel.setText( currentUser.getUsername());
    }
    public void switchtologin(ActionEvent event)  throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Login page1.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchtoupload(ActionEvent event)  throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("uploadlessons.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        // stage.setFullScreen(true);
        stage.show();
    }


    public void switchtologin_m(MouseEvent event)  throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Login page1.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        // stage.setFullScreen(true);
        stage.show();
    }





    public void switchtouserprofile(MouseEvent event)  throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("StudentUser.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        // stage.setFullScreen(true);
        stage.show();
    }





    public void switchtosignup(ActionEvent event) throws IOException
    {

         root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
      //  stage.setFullScreen(true);
        stage.show();

    }

    public void  switchtodashboard(ActionEvent event) throws IOException
    {

        root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
       // stage.setFullScreen(true);
        stage.show();

    }

    public void switchtosearch(ActionEvent event)  throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("SearchBarPage.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
       // stage.setFullScreen(true);
        stage.show();
    }


    public void switchtodictionary(ActionEvent event) throws IOException
    {

        //root = FXMLLoader.load(getClass().getResource("DictionaryView.fxml"));
       root = FXMLLoader.load(getClass().getResource("dictionary_hier.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
       // stage.setFullScreen(true);
        stage.show();

    }
    public void switchtolesson(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("lessons.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
       // stage.setFullScreen(true);
        stage.show();
    }


    public void switchtolearn(ActionEvent event) throws IOException
    {

        root = FXMLLoader.load(getClass().getResource("learn.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
       // stage.setFullScreen(true);
       stage.show();

    }


    public void switchtoprog(ActionEvent event) throws IOException
    {

        root = FXMLLoader.load(getClass().getResource("UserProgress.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();

    }

    public void switchtoleaderboard(ActionEvent event) throws IOException
    {

        root = FXMLLoader.load(getClass().getResource("leaderbaord.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
      //  stage.setFullScreen(true);
        stage.show();

    }


    public void switchtoquiz(ActionEvent event) throws IOException
    {

        root = FXMLLoader.load(getClass().getResource("Quiz01.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
       // stage.setFullScreen(true);
        stage.show();

    }

    public void switchtowelcome(ActionEvent event) throws IOException
    {

        root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
       // stage.setFullScreen(true);
        stage.show();
    }





}


