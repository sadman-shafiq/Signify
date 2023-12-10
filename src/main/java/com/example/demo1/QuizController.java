//package com.example.demo1;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.HBox;
//import javafx.stage.Modality;
//
//public class QuizController {
//
//    @FXML
//    private HBox optionsHBox;
//    @FXML
//    private ImageView correctImageView;
//
//    @FXML
//    private ImageView option1ImageView;
//
//    @FXML
//    private ImageView option2ImageView;
//
//    @FXML
//    private ImageView option3ImageView;
//
//    @FXML
//    private ImageView option4ImageView;
//
//    @FXML
//    private Button checkAnswerButton;
//
//    private ImageView selectedOption;
//
//    private String correctAnswerUrl = "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\alphabets\\K.png"; // Replace with the correct answer URL
//
//    @FXML
//    private void initialize() {
//        initializeOptions();
//        initializeEventHandlers();
//    }
//
//    private void initializeOptions() {
//        // Load images for options
//        loadImage(option1ImageView, "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\alphabets\\C.png");
//        loadImage(option2ImageView, "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\alphabets\\K.png");
//        loadImage(option3ImageView, "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\alphabets\\C.png");
//        loadImage(option4ImageView, "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\alphabets\\C.png");
//
//        // Set initial opacity
//        setInitialOpacity(option1ImageView);
//        setInitialOpacity(option2ImageView);
//        setInitialOpacity(option3ImageView);
//        setInitialOpacity(option4ImageView);
//    }
//
//    private void setInitialOpacity(ImageView imageView) {
//        imageView.setOpacity(1.0);
//    }
//
//    private void loadImage(ImageView optionImageView, String imageUrl) {
//        optionImageView.setImage(new Image(imageUrl));
//        // Add additional setup as needed
//    }
//
//    private void initializeEventHandlers() {
//        setHoverHandlers(option1ImageView);
//        setHoverHandlers(option2ImageView);
//        setHoverHandlers(option3ImageView);
//        setHoverHandlers(option4ImageView);
//
//        option1ImageView.setOnMouseClicked(this::handleOptionClick);
//        option2ImageView.setOnMouseClicked(this::handleOptionClick);
//        option3ImageView.setOnMouseClicked(this::handleOptionClick);
//        option4ImageView.setOnMouseClicked(this::handleOptionClick);
//    }
//
//    private void setHoverHandlers(ImageView imageView) {
//        imageView.setOnMouseEntered(event -> {
//            imageView.setOpacity(0.4);
//        });
//
//        imageView.setOnMouseExited(event -> {
//            if (imageView != selectedOption) {
//                imageView.setOpacity(1.0);
//            }
//        });
//    }
//
//    private void handleOptionClick(MouseEvent event) {
//        ImageView optionImageView = (ImageView) event.getSource();
//        highlightOption(optionImageView);
//    }
//
//    private void highlightOption(ImageView selectedOption) {
//        if (this.selectedOption != null) {
//            this.selectedOption.getStyleClass().remove("selected-option");
//            this.selectedOption.getStyleClass().removeAll("correct-answer", "wrong-answer");
//            this.selectedOption.setOpacity(1.0);
//        }
//
//        this.selectedOption = selectedOption;
//        selectedOption.getStyleClass().add("selected-option");
//        //selectedOption.setOpacity(0.4);
//    }
//
//    @FXML
//    private void checkAnswer() {
//        if (selectedOption != null) {
//            String selectedAnswerUrl = selectedOption.getImage().getUrl();
//
//            if (selectedAnswerUrl.equals(correctAnswerUrl)) {
//                selectedOption.getStyleClass().add("correct-answer");
//                loadImage(correctImageView, "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\Styles\\correct.png");
//                //showResultDialog("Correct!", "Congratulations, you selected the correct answer!");
//            } else {
//                selectedOption.getStyleClass().add("wrong-answer");
//                loadImage(correctImageView, "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\Styles\\incorrect.png");
//               // showResultDialog("Incorrect", "Sorry, the correct answer is different.");
//            }
//        }
//    }
//
//    private void showResultDialog(String title, String content) {
//        Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
//        resultAlert.setTitle(title);
//        resultAlert.setHeaderText(null);
//        resultAlert.setContentText(content);
//        resultAlert.initModality(Modality.APPLICATION_MODAL);
//        resultAlert.showAndWait();
//    }
//}
package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import static com.example.demo1.HelloApplication.connection;

public class QuizController {
    User currentUser = User.getCurrentUser();

    @FXML
    private HBox optionsHBox;

    @FXML
    private ImageView correctImageView;

    @FXML
    private ImageView option1ImageView;

    @FXML
    private ImageView option2ImageView;

    @FXML
    private ImageView option3ImageView;

    @FXML
    private ImageView option4ImageView;

    @FXML
    private Label questionLabel;




    private ImageView selectedOption;

    private List<QuizQuestion> quizQuestions;
    private int currentQuestionIndex;
    private Parent root;
    private Stage stage;

    private Scene scene;

    @FXML
    private void initialize() {
        loadQuizData();
        initializeOptions();
        initializeEventHandlers();
        showQuestion();
    }

    private void loadQuizData() {
        quizQuestions = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM quiz_question")) {

            while (resultSet.next()) {
                String correctAnswerPath = resultSet.getString("correct_answer_path");
                String option1Path = resultSet.getString("option1_path");
                String option2Path = resultSet.getString("option2_path");
                String option3Path = resultSet.getString("option3_path");
                String option4Path = resultSet.getString("option4_path");
                String question= "What is the sign for " +resultSet.getString("name")+" ?";

                QuizQuestion quizQuestion = new QuizQuestion(correctAnswerPath, option1Path, option2Path, option3Path, option4Path, question);
                quizQuestions.add(quizQuestion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeOptions() {
        currentQuestionIndex = 0;
        showQuestion();
    }

    public void  switchtodashboard(MouseEvent event) throws IOException
    {

        root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        // stage.setFullScreen(true);
        stage.show();

    }

    private void showQuestion() {
        if (currentQuestionIndex < quizQuestions.size()) {

            QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);

            loadImage(option1ImageView, currentQuestion.getOption1Path());
            loadImage(option2ImageView, currentQuestion.getOption2Path());

            loadImage(option3ImageView, currentQuestion.getOption3Path());

            loadImage(option4ImageView, currentQuestion.getOption4Path());
            questionLabel.setText(currentQuestion.getquestion());


            setInitialOpacity(option1ImageView);
            setInitialOpacity(option2ImageView);
            setInitialOpacity(option3ImageView);
            setInitialOpacity(option4ImageView);



        }
    }





    private void setInitialOpacity(ImageView imageView) {
        imageView.setOpacity(1.0);
        imageView.getStyleClass().clear();
    }

    private void loadImage(ImageView optionImageView, String imageUrl) {
        optionImageView.setImage(new Image(imageUrl));
    }

    private void initializeEventHandlers() {
        setHoverHandlers(option1ImageView);
        setHoverHandlers(option2ImageView);
        setHoverHandlers(option3ImageView);
        setHoverHandlers(option4ImageView);

        option1ImageView.setOnMouseClicked(this::handleOptionClick);
        option2ImageView.setOnMouseClicked(this::handleOptionClick);
        option3ImageView.setOnMouseClicked(this::handleOptionClick);
        option4ImageView.setOnMouseClicked(this::handleOptionClick);
    }

    private void setHoverHandlers(ImageView imageView) {
        imageView.setOnMouseEntered(event -> {
            imageView.setOpacity(0.4);
        });

        imageView.setOnMouseExited(event -> {
            if (imageView != selectedOption) {
                imageView.setOpacity(1.0);
            }
        });
    }

    private void handleOptionClick(MouseEvent event) {
        ImageView optionImageView = (ImageView) event.getSource();
        highlightOption(optionImageView);
    }

    private void highlightOption(ImageView selectedOption) {
        if (this.selectedOption != null) {
            this.selectedOption.getStyleClass().remove("selected-option");
            this.selectedOption.getStyleClass().removeAll("correct-answer", "wrong-answer");
            this.selectedOption.setOpacity(1.0);
        }

        this.selectedOption = selectedOption;
        selectedOption.getStyleClass().add("selected-option");
    }

    @FXML
    private void checkAnswer() {
        if (!quizQuestions.isEmpty() && selectedOption != null && currentQuestionIndex < quizQuestions.size()) {
            String selectedAnswerPath = selectedOption.getImage().getUrl();
            QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);

            if (selectedAnswerPath.equals(currentQuestion.getCorrectAnswerPath())) {
                loadImage(correctImageView, "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\Styles\\correct.png");
                selectedOption.getStyleClass().add("correct-answer");

            } else {
                loadImage(correctImageView, "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\Styles\\incorrect.png");
                selectedOption.getStyleClass().add("wrong-answer");

            }


            currentQuestionIndex++;

        } else {
            showResultDialog("Quiz Completed", "You have completed the quiz!");
        }
    }



    @FXML
    void show_q(ActionEvent event) {
        showQuestion();
        correctImageView.setImage(null);

    }


    private void showResultDialog(String title, String content) {
        Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
        resultAlert.setTitle(title);
        resultAlert.setHeaderText(null);
        resultAlert.setContentText(content);
        resultAlert.initModality(Modality.APPLICATION_MODAL);
        resultAlert.showAndWait();
    }

    private static class QuizQuestion {
        private final String correctAnswerPath;

        private final String question;
        private final String option1Path;
        private final String option2Path;
        private final String option3Path;
        private final String option4Path;



        public QuizQuestion(String correctAnswerPath, String option1Path, String option2Path, String option3Path, String option4Path, String question) {
            this.correctAnswerPath = correctAnswerPath;
            this.option1Path = option1Path;
            this.option2Path = option2Path;
            this.option3Path = option3Path;
            this.option4Path = option4Path;
            this.question= question;
        }

        public String getCorrectAnswerPath() {
            return correctAnswerPath;
        }

        public String getOption1Path() {
            return option1Path;
        }

        public String getOption2Path() {
            return option2Path;
        }

        public String getOption3Path() {
            return option3Path;
        }

        public String getOption4Path() {
            return option4Path;
        }

        public String getquestion() {
            return question;
        }
    }
}
