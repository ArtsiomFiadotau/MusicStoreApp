package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.regex.Pattern;

public class FeedbackController {
    @FXML
    private TextField nameField; // Поле для ввода имени
    @FXML
    private TextField emailField; // Поле для ввода email
    @FXML
    private TextField subjectField; // Поле для ввода темы
    @FXML
    private TextField messageArea; // Поле для ввода сообщения
    @FXML
    private Label labelNameError; // Label для отображения ошибки имени
    @FXML
    private Label labelEmailError; // Label для отображения ошибки email
    @FXML
    private Label labelSubjectError; // Label для отображения ошибки темы
    @FXML
    private Label labelMessageError; // Label для отображения ошибки сообщения

    @FXML
    public void initialize() {
        // Обработчик потери фокуса для поля имени
        nameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // если поле потеряло фокус
                validateName(); // Проверка имени
            } else {
                labelNameError.setText(""); // Очищаем сообщение об ошибке при входе в поле
            }
        });

        // Обработчик потери фокуса для поля email
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // если поле потеряло фокус
                validateEmail(); // Проверка email
            } else {
                labelEmailError.setText(""); // Очищаем сообщение об ошибке при входе в поле
            }
        });

        // Обработчик потери фокуса для поля subject
        subjectField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // если поле потеряло фокус
                validateSubject(); // Проверка темы
            } else {
                labelSubjectError.setText(""); // Очищаем сообщение об ошибке при входе в поле
            }
        });

        // Обработчик потери фокуса для поля message
        messageArea.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // если поле потеряло фокус
                validateMessage(); // Проверка сообщения
            } else {
                labelMessageError.setText(""); // Очищаем сообщение об ошибке при входе в поле
            }
        });
    }

    private void validateName() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            labelNameError.setText("Имя не должно быть пустым.");
        } else if (!isNameValid(name)) {
            labelNameError.setText("Имя может содержать только EN и RU буквы.");
        } else {
            labelNameError.setText(""); // Убираем ошибку, если имя корректно
        }
    }

    private void validateEmail() {
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            labelEmailError.setText("Email не должен быть пустым.");
        } else if (!isEmailValid(email)) {
            labelEmailError.setText("Введите корректный email адрес.");
        } else {
            labelEmailError.setText(""); // Убираем ошибку, если email корректен
        }
    }

    private void validateSubject() {
        String subject = subjectField.getText().trim();
        if (subject.isEmpty()) {
            labelSubjectError.setText("Тема не должна быть пустой.");
        } else {
            labelSubjectError.setText(""); // Убираем ошибку, если тема корректна
        }
    }

    private void validateMessage() {
        String message = messageArea.getText().trim();
        if (message.isEmpty()) {
            labelMessageError.setText("Сообщение не должно быть пустым.");
        } else {
            labelMessageError.setText(""); // Убираем ошибку, если сообщение корректно
        }
    }

    @FXML
    public void handleSendFeedback() {
        // Применяем валидацию для всех полей перед выполнением других логик
        validateName();
        validateEmail();
        validateSubject();
        validateMessage();

        // Проверка, если есть ошибки валидации
        if (!labelNameError.getText().isEmpty() ||
                !labelEmailError.getText().isEmpty() ||
                !labelSubjectError.getText().isEmpty() ||
                !labelMessageError.getText().isEmpty()) {
            showAlert("Ошибка ввода", "Пожалуйста, исправьте ошибки перед отправкой.");
            return;
        }

        // Если все поля заполнены корректно, продолжаем с обработкой отправки обратной связи
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String subject = subjectField.getText().trim();
        String message = messageArea.getText().trim();

        // Логика для обработки отправки обратной связи (например, сохранение в файл или БД)

        // Очистка полей после отправки
        nameField.clear();
        emailField.clear();
        subjectField.clear();
        messageArea.clear();

        // Показать уведомление об успешной отправке
        showAlert("Успех", "Ваше сообщение отправлено!");
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