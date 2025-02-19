package main.java.gui;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.stage.Stage;
import main.java.model.WindowUtils; // Import WindowUtils class
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupportPageController implements Initializable {
    @FXML
    private Label infoLabel1;

    @FXML
    private Hyperlink infoLabel3;

    @FXML
    private TextField mailTextField;

    @FXML
    private Button returnButton;


    @FXML
    private Label stillProblemLabel;

    @FXML
    private Label warningText;

    public void close() {
        System.exit(0);
    }

    @FXML
    private void onReturnButtonClicked() {
        try {
            Parent returnRoot = FXMLLoader.load(getClass().getResource("../../resources/forms/FirstScene.fxml"));
            Stage stage = (Stage) returnButton.getScene().getWindow();
            Scene newScene = new Scene(returnRoot);
            stage.setScene(newScene);
            WindowUtils.applyWindowFeatures(stage, returnRoot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSendButtonClicked() {
        if (!mailTextField.getText().trim().isEmpty()) {
            infoLabel1.setVisible(true);
            infoLabel3.setVisible(true);
            warningText.setVisible(false);
        } else {
            warningText.setVisible(true);
        }
    }

    @FXML
    private void onProblemButtonClicked() {
        stillProblemLabel.setVisible(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infoLabel1.setVisible(false);
        infoLabel3.setVisible(false);
        warningText.setVisible(false);
        stillProblemLabel.setVisible(false);
    }
}
