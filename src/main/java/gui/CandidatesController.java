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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.election.Candidate;
import main.java.election.Session;
import main.java.model.AlreadyCandidateException;
import main.java.model.NotEnoughHoneyException;
import main.java.model.ReachedMaxException;
import main.java.model.WindowUtils;
import main.java.election.ElectionSystem;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CandidatesController implements Initializable {

    @FXML
    private Hyperlink actualitiesLink;

    @FXML
    private Button candidateButton;

    @FXML
    private Hyperlink candidatesLink;

    @FXML
    private Button closeButton;

    @FXML
    private Hyperlink gainHoneyLink;

    @FXML
    private Button homeButton;

    @FXML
    private Label honeyLabel1;

    @FXML
    private Label honeyLabel2;

    @FXML
    private Label honeyLabel3;

    @FXML
    private Label honeyLabel4;

    @FXML
    private Label honeyLabel5;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nameLabel1;

    @FXML
    private Label nameLabel2;

    @FXML
    private Label nameLabel3;

    @FXML
    private Label nameLabel4;

    @FXML
    private Label nameLabel5;

    @FXML
    private Hyperlink resultsLink;

    @FXML
    private Label roleLabel1;

    @FXML
    private Label roleLabel2;

    @FXML
    private Label roleLabel3;

    @FXML
    private Label roleLabel4;

    @FXML
    private Label roleLabel5;

    @FXML
    private Hyperlink signOutLink;

    @FXML
    private Label userStatusLabel;

    @FXML
    private Hyperlink voteLink;

    private List<Candidate> candidates;

    @FXML
    private Label WarningLabel;

    private ElectionSystem electionSystem;

    public void initElectionSystem(ElectionSystem electionSystem) {
        this.electionSystem = electionSystem;
        setupCandidates();
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

    @FXML
    private void onCandidateButtonClicked() {
        try {
            Candidate newCandidate = new Candidate(Session.getUsername(), Session.getRole(), Session.getHoneyProduced());
            electionSystem.addCandidate(newCandidate);
            populateCandidateLabels();
            Session.setIsCandidate(true);
            WarningLabel.setVisible(false);
        } catch (ReachedMaxException | AlreadyCandidateException | NotEnoughHoneyException e) {
            WarningLabel.setText(e.getMessage());
            WarningLabel.setVisible(true);
        }
    }

    private void updateUserStatus() {
        String username = Session.getUsername();
        userStatusLabel.setText(username);
    }

    private void populateCandidateLabels() {
        Label[] nameLabels = { nameLabel1, nameLabel2, nameLabel3, nameLabel4, nameLabel5 };
        Label[] roleLabels = { roleLabel1, roleLabel2, roleLabel3, roleLabel4, roleLabel5 };
        Label[] honeyLabels = { honeyLabel1, honeyLabel2, honeyLabel3, honeyLabel4, honeyLabel5 };
        for (int i = 0; i < 5; i++) {
            if (candidates != null && i < candidates.size()) {
                nameLabels[i].setText("Name: " + candidates.get(i).getName());
                roleLabels[i].setText("Role: " + candidates.get(i).getRole());
                if (Objects.equals(candidates.get(i).getName(), Session.getUsername())) {
                    honeyLabels[i].setText("Honey: " + String.valueOf(Session.getHoneyProduced()) + " litres");
                }
                honeyLabels[i].setText("Honey: " + String.valueOf(candidates.get(i).getHoneyProduced()) + " litres");
            } else {
                nameLabels[i].setText("");
                roleLabels[i].setText("");
                honeyLabels[i].setText("");
            }
        }
    }

    private void setupCandidates() {
        if (electionSystem != null) {
            candidates = electionSystem.getCandidates();
            populateCandidateLabels();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WarningLabel.setVisible(false);
        updateUserStatus();
    }
}
