

package com.example.demo1;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;

import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.util.Duration;


import java.sql.Connection;
import java.sql.SQLException;


public class HelloApplication extends Application {

    static Connection connection;
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent splashRoot = FXMLLoader.load(getClass().getResource("Splash.fxml"));
            Scene splashScene = new Scene(splashRoot);


            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            primaryStage.setWidth(bounds.getWidth());
            primaryStage.setHeight(bounds.getHeight());

            primaryStage.setResizable(false);
            primaryStage.setScene(splashScene);
            primaryStage.show();






            FadeTransition fadeIn = new FadeTransition(Duration. seconds (4), splashRoot);
            fadeIn.setFromValue (0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            FadeTransition fadeOut = new FadeTransition(Duration. seconds (4), splashRoot);
            fadeOut.setFromValue(1);
            fadeOut. setToValue (0);
            fadeOut.setCycleCount(1);
            fadeIn.play();
            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
                    });

            fadeOut.setOnFinished((e) -> {
                try {
                    loadLoginScreen(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    private void loadLoginScreen(Stage stage) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Login page1.fxml"));
        Scene loginScene = new Scene(loginRoot);

        connection = sql_connection.getConnection();

        stage.setScene(loginScene);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(e -> {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });


    }


    public static void main(String[] args) {
        launch();
    }
}









//
//package com.example.demo1;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Rectangle2D;
//import javafx.scene.Scene;
//import javafx.scene.Parent;
//import javafx.stage.Stage;
//import javafx.stage.Screen;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//
//public class HelloApplication extends Application {
//
//    static Connection connection;
//    @Override
//    public void start(Stage stage)  {
//
//        try {
//
//            Parent root = FXMLLoader.load(getClass().getResource("Login page1.fxml"));
//            Scene scene = new Scene(root);//880,550
//            //  stage.setFullScreen(true);
//            stage.setScene(scene);
//
//            stage.setResizable(false);
//            connection = sql_connection.getConnection();
//            // stage.setFullScreen(true);
//            Screen screen = Screen.getPrimary();
//            Rectangle2D bounds = screen.getVisualBounds();
//
//            stage.setWidth(bounds.getWidth());
//            stage.setHeight(bounds.getHeight());
//            stage.show();
//            stage.setOnCloseRequest(e -> {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            });
//
//        } catch(Exception e) {
//
//            e.printStackTrace();
//        }
//
//
//
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}
//
//
//
//
//
//
//
//
//
