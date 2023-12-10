
package com.example.demo1;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.demo1.HelloApplication.connection;

public class UserProgress implements Initializable {

    User currentUser = User.getCurrentUser();
    private Stage stage;
    private Scene scene;

    private  Parent root;
    @FXML
    public PieChart pieChart;
    @FXML
    public LineChart<String, Number> monthlychart;
    @FXML
    public ChoiceBox<String> choicebox;
    @FXML
    public Label totalQuestions;

    public XYChart.Series<String, Number> series = new XYChart.Series<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        if (totalQuestions != null) totalQuestions.setText(currentUser.getTotalQuestions() + "");
        double tq=currentUser.getTotalQuestions();
        double value = tq/3.0;

        PieChart.Data completedSlice = new PieChart.Data("Completed", value);
        PieChart.Data incompleteSlice = new PieChart.Data("Incomplete", 100.0 - value);

        pieChart.getData().addAll(completedSlice, incompleteSlice);

        completedSlice.getNode().setStyle("-fx-pie-color: #4CAF50;");
        incompleteSlice.getNode().setStyle("-fx-pie-color:  #FF5733;");

        applyHoverEffect(completedSlice);
        applyHoverEffect(incompleteSlice);

        loadLessonsData();




    }




    private void loadLessonsData() {
        series.getData().clear();
        String username = currentUser.getUsername();

        String query = "SELECT date, lessons_learned FROM userdata WHERE username = ?";

        try (//Connection connection = HelloApplication.connection;
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String date = resultSet.getString("date");
                    int lessonsLearned = resultSet.getInt("lessons_learned");
                    series.getData().add(new XYChart.Data<>(date, lessonsLearned));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        monthlychart.getData().add(series);
    }

    public void switchtodashboard(MouseEvent event) throws IOException
    {

        root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void applyHoverEffect(PieChart.Data slice) {
        slice.getNode().setOnMouseEntered(e -> slice.getNode().setScaleX(1.1));
        slice.getNode().setOnMouseExited(e -> slice.getNode().setScaleX(1.0));
    }




}
