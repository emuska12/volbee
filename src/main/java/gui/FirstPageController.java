package main.java.gui;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import main.java.election.Session;
import main.java.etnities.Bee;
import main.java.model.WindowUtils;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.List;
import java.util.ResourceBundle;
import main.java.election.ElectionSystem;

public class FirstPageController implements Initializable {
    @FXML private Button loginButton;
    @FXML private Hyperlink forgottenButton;
    @FXML private PasswordField passwordTextField;
    @FXML private TextField usernameTextField;
    @FXML private Label warningText;
    @FXML private Label loginError;

    private ElectionSystem electionSystem;

    public void initElectionSystem(ElectionSystem electionSystem) {
        this.electionSystem = electionSystem;
    }

    public void close() {
        System.exit(0);
    }

    @FXML
    private void onForgottenButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/forms/ForgottenPassword.fxml"));
            Parent forgottenRoot = loader.load();
            FirstPageController controller = loader.getController();
            controller.initElectionSystem(electionSystem); // Pass the instance

            Stage stage = (Stage) forgottenButton.getScene().getWindow();
            Scene newScene = new Scene(forgottenRoot);
            stage.setScene(newScene);
            WindowUtils.applyWindowFeatures(stage, forgottenRoot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSignUpButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/forms/RegisterPage.fxml"));
            Parent registerRoot = loader.load();
            FirstPageController controller = loader.getController();
            controller.initElectionSystem(electionSystem); // Pass the instance

            Stage stage = (Stage) forgottenButton.getScene().getWindow();
            Scene newScene = new Scene(registerRoot);
            stage.setScene(newScene);
            WindowUtils.applyWindowFeatures(stage, registerRoot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        warningText.setVisible(false);
        loginError.setVisible(false);

        loginButton.setOnAction(event -> {
            if (usernameTextField.getText().trim().isEmpty() || passwordTextField.getText().trim().isEmpty()) {
                warningText.setVisible(true);
            } else {
                warningText.setVisible(false);
                if (electionSystem.validateCredentials(usernameTextField.getText(), passwordTextField.getText())) {
                    try {
                        Bee userBee = electionSystem.getUser(usernameTextField.getText());
                        String username = userBee.getName();
                        String role = userBee.getRole();
                        int honeyProduced = userBee.getHoneyProduced();
                        boolean hasVoted = electionSystem.hasUserVoted(username);
                        boolean isCandidate = electionSystem.isUserCandidate(username);
                        int votesGained = electionSystem.getVotesForCandidate(username);
                        Session.setUserDetails(username, role, honeyProduced, hasVoted, isCandidate, votesGained);

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/forms/Hub.fxml"));
                        Parent hubRoot = loader.load();
                        HubController hubController = loader.getController();
                        hubController.initElectionSystem(electionSystem); // Pass the instance

                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        Scene newScene = new Scene(hubRoot);
                        stage.setScene(newScene);
                        WindowUtils.applyWindowFeatures(stage, hubRoot);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    loginError.setVisible(true);
                }
            }
        });
    }
}
