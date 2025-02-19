package main.java.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.election.ElectionSystem;
import main.java.model.WindowUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPageContoller implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField honeyField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameField;

    @FXML
    private Label warningLabel;

    @FXML
    private Label existsLabel;

    @FXML
    private Hyperlink loginLink;

    public void close() {
        System.exit(0);
    }

    @FXML
    private void onLoginButtonClicked() {
        try {
            Parent returnRoot = FXMLLoader.load(getClass().getResource("../../resources/forms/FirstScene.fxml"));
            Stage stage = (Stage) loginLink.getScene().getWindow();
            Scene newScene = new Scene(returnRoot);
            stage.setScene(newScene);
            WindowUtils.applyWindowFeatures(stage, returnRoot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        loginLink.setVisible(false);
        existsLabel.setVisible(false);
        ElectionSystem electionSystem = new ElectionSystem();
        choiceBox.getItems().addAll("Worker", "Drone", "Queen");
        warningLabel.setVisible(false);
        registerButton.setOnAction(event -> {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || honeyField.getText().isEmpty() || choiceBox.getValue() == null) {
                warningLabel.setVisible(true);
            } else {
                warningLabel.setVisible(false);
                if (electionSystem.signUpUser(usernameField.getText(), passwordField.getText(), honeyField.getText(), choiceBox.getValue()) == 1) {
                    loginLink.setVisible(true);
                    existsLabel.setVisible(true);
                } else {
                    try {
                        Parent loginPage = FXMLLoader.load(getClass().getResource("../../resources/forms/FirstScene.fxml"));
                        Stage stage = (Stage) registerButton.getScene().getWindow();
                        Scene newScene = new Scene(loginPage);
                        stage.setScene(newScene);
                        WindowUtils.applyWindowFeatures(stage, loginPage);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
