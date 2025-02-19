package main.java.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.election.ElectionSystem;
import main.java.election.Session;
import main.java.model.WindowUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActualitiesController implements Initializable {

    @FXML
    private Hyperlink actualitiesLink;

    @FXML
    private Hyperlink candidatesLink;

    @FXML
    private Button closeButton;

    @FXML
    private Hyperlink gainHoneyLink;

    @FXML
    private Button homeButton;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Hyperlink resultsLink;

    @FXML
    private Hyperlink signOutLink;

    @FXML
    private TextArea textArea;

    @FXML
    private Label userStatusLabel;

    @FXML
    private Hyperlink voteLink;

    private ElectionSystem electionSystem;

    public void initElectionSystem(ElectionSystem electionSystem) {
        this.electionSystem = electionSystem;
    }

    public void close() {
        System.exit(0);
    }

    @FXML
    private void onCandidates() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/forms/Candidates.fxml"));
            Parent Root = loader.load();
            CandidatesController candidatesController = loader.getController();
            candidatesController.initElectionSystem(electionSystem);
            Stage stage = (Stage) signOutLink.getScene().getWindow();
            Scene newScene = new Scene(Root);
            stage.setScene(newScene);
            WindowUtils.applyWindowFeatures(stage, Root);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onVote() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/forms/Vote.fxml"));
            Parent Root = loader.load();
            VoteController voteController = loader.getController();
            voteController.initElectionSystem(electionSystem);
            Stage stage = (Stage) voteLink.getScene().getWindow();
            Scene newScene = new Scene(Root);
            stage.setScene(newScene);
            WindowUtils.applyWindowFeatures(stage, Root);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onActualities() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/forms/Actualities.fxml"));
            Parent Root = loader.load();
            ActualitiesController actualitiesController = loader.getController();
            actualitiesController.initElectionSystem(electionSystem);
            Stage stage = (Stage) actualitiesLink.getScene().getWindow();
            Scene newScene = new Scene(Root);
            stage.setScene(newScene);
            WindowUtils.applyWindowFeatures(stage, Root);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void onGainHoney() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/forms/GainHoney.fxml"));
            Parent Root = loader.load();
            GainHoneyController gainHoneyController = loader.getController();
            gainHoneyController.initElectionSystem(electionSystem);
            Stage stage = (Stage) gainHoneyLink.getScene().getWindow();
            Scene newScene = new Scene(Root);
            stage.setScene(newScene);
            WindowUtils.applyWindowFeatures(stage, Root);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onResults() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/forms/Results.fxml"));
            Parent Root = loader.load();
            ResultsController resultsController = loader.getController();
            resultsController.initElectionSystem(electionSystem);
            Stage stage = (Stage) resultsLink.getScene().getWindow();
            Scene newScene = new Scene(Root);
            stage.setScene(newScene);
            WindowUtils.applyWindowFeatures(stage, Root);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSignOut() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/forms/FirstScene.fxml"));
            Parent Root = loader.load();
            FirstPageController firstPageController = loader.getController();
            firstPageController.initElectionSystem(electionSystem);
            Stage stage = (Stage) signOutLink.getScene().getWindow();
            Scene newScene = new Scene(Root);
            stage.setScene(newScene);
            WindowUtils.applyWindowFeatures(stage, Root);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/forms/Hub.fxml"));
            Parent hubRoot = loader.load();
            HubController hubController = loader.getController();
            hubController.initElectionSystem(electionSystem); // Pass the instance
            Stage stage = (Stage) homeButton.getScene().getWindow();
            Scene newScene = new Scene(hubRoot);
            stage.setScene(newScene);
            WindowUtils.applyWindowFeatures(stage, hubRoot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateUserStatus() {
        String username = Session.getUsername();
        userStatusLabel.setText(username);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateUserStatus();
    }
}
