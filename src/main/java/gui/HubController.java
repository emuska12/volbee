package main.java.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.election.ElectionSystem;
import main.java.election.Session;
import main.java.etnities.Bee;
import main.java.model.WindowUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HubController implements Initializable{

    @FXML
    private Hyperlink actualitiesLink;

    @FXML
    private Label alreadyVotedLabel;

    @FXML
    private Hyperlink candidatesLink;

    @FXML
    private Button closeButton;

    @FXML
    private Hyperlink gainHoneyLink;

    @FXML
    private Button homeButton;

    @FXML
    private Label honeyLabel;

    @FXML
    private Label isCandidateLabel;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nameLabel;

    @FXML
    private Hyperlink resultsLink;

    @FXML
    private Label roleLabel;

    @FXML
    private Hyperlink signOutLink;

    @FXML
    private Label userStatusLabel;

    @FXML
    private Hyperlink voteLink;

    @FXML
    private Label votesAmountLabel;

    private ElectionSystem electionSystem;

    public void initElectionSystem(ElectionSystem electionSystem) {
        this.electionSystem = electionSystem;
    }
    public void updateUserInfo(Bee user) {
        updateStatusLabel(user.getName());
        updateNameLabel(user.getName());
        updateRoleLabel(user.getRole());
        updateHoneyLabel(String.valueOf(user.getHoneyProduced()));

    }
    public void updateStatusLabel(String username) {
        userStatusLabel.setText(username);
    }

    public void updateNameLabel(String label) {
        nameLabel.setText(label);
    }

    public void updateVotedStatus(boolean hasVoted) {
        alreadyVotedLabel.setText(hasVoted ? "YES" : "NO");
    }

    public void updateRoleLabel(String label) {
        roleLabel.setText(label);
    }

    public void updateHoneyLabel(String label) {
        honeyLabel.setText(label);
    }

    public void updateCandidateStatus(boolean isCandidate) {
        isCandidateLabel.setText(isCandidate ? "YES" : "NO");
    }

    public void updateVotesAmount(String votes) {
        votesAmountLabel.setText(votes);
    }


    @FXML
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

    private void updateUserProfile() {
        userStatusLabel.setText(Session.getUsername());
        nameLabel.setText(Session.getUsername());
        roleLabel.setText(Session.getRole());
        honeyLabel.setText(String.valueOf(Session.getHoneyProduced()));
        if (Session.hasVoted()) {
            alreadyVotedLabel.setText("YES");
        }
        else {
            alreadyVotedLabel.setText("NO");
        }
        if (Session.isCandidate()) {
            isCandidateLabel.setText("YES");
            votesAmountLabel.setText(String.valueOf(Session.getVotesGained()));
        }
        else {
            isCandidateLabel.setText("NO");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateUserProfile();
    }
}
