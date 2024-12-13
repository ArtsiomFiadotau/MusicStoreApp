package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AuthorController {
    @FXML
    private Button toMainButton; // Изменено на правильное имя
    @FXML
    private Button toAuthorButton; // Изменено на правильное имя
    @FXML
    private Button toFeedbackButton; // Изменено на правильное имя
    @FXML
    private Button toStartButton; // Изменено на правильное имя
    @FXML
    private ImageView imageView; // Это изображение, если его хотите использовать

    @FXML
    private void handleToMain(ActionEvent event) {
        System.out.println("Navigating to the main shop page...");
        loadScene("/view/Main.fxml", event);
    }

    @FXML
    private void handleToAuthor(ActionEvent event) {
        System.out.println("Navigating to the author page...");
        loadScene("/view/Author.fxml", event);
    }

    @FXML
    private void handleToFeedback(ActionEvent event) {
        System.out.println("Navigating to the feedback page...");
        loadScene("/view/Feedback.fxml", event);
    }

    @FXML
    private void handleToStart(ActionEvent event) {
        System.out.println("Navigating to the start page...");
        loadScene("/view/Start.fxml", event);
    }

    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Получаем Stage из события
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}