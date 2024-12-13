package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private ListView<String> albumList;

    public void initialize() {
        // Здесь можно инициализировать список альбомов
        albumList.getItems().addAll("Альбом 1", "Альбом 2", "Альбом 3");
    }

    @FXML
    public void handleToMain() {
        // Логика для перехода на основную страницу
    }

    @FXML
    public void handleToAuthor() {
        // Логика для перехода на страницу "Об авторе"
    }

    @FXML
    public void handleToFeedback() {
        // Логика для перехода на страницу обратной связи
    }
}