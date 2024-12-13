package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import java.util.regex.Pattern;

public class FeedbackController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField; // Добавляем поле для email
    @FXML
    private TextField subjectField;
    @FXML
    private TextArea messageArea;

    @FXML
    public void handleSendFeedback() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim(); // Получаем значение из поля email
        String subject = subjectField.getText().trim();
        String message = messageArea.getText().trim();

        if (name.isEmpty() || email.isEmpty() || subject.isEmpty() ||message.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, заполните все поля.");
            alert.showAndWait();
            return;
        }

        // Проверка корректности имени
        if (!isNameValid(name)) {
            showAlert("Ошибка ввода", "Имя может содержать только буквы русского и английского алфавита.");
            return;
        }

        // Проверка корректности email
        if (!isEmailValid(email)) {
            showAlert("Ошибка ввода", "Введите корректный email адрес.");
            return;
        }

        // Логика для обработки отправки обратной связи (например, сохранение в файл или БД)

        // Очистка полей после отправки
        nameField.clear();
        emailField.clear(); // Очищаем поле email
        messageArea.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успех");
        alert.setHeaderText(null);
        alert.setContentText("Ваше сообщение отправлено!");
        alert.showAndWait();
    }

    private boolean isNameValid(String name) {
        return name.matches("^[a-zA-Zа-яА-ЯёЁ\\s]+$"); // Проверка на буквы и пробелы
    }

    private boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; // Простое регулярное выражение для проверки email
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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