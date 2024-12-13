package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button toMainButton;
    @FXML
    private Button toAuthorButton;
    @FXML
    private Button toFeedbackButton;
    @FXML
    private Button toStartButton;
    @FXML
    private ImageView imageView;

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