package main.java.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.election.Candidate;
import main.java.election.ElectionSystem;
import main.java.election.Session;
import main.java.model.WindowUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {
    @FXML
    private BarChart<String, Number> votesBarChart;
    @FXML
    private Label WarningLabel;

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
    private Hyperlink signOutLink;

    @FXML
    private Label userStatusLabel;

    @FXML
    private Hyperlink voteLink;

    @FXML
    private Label votesLabel1;

    @FXML
    private Label votesLabel2;

    @FXML
    private Label votesLabel3;

    @FXML
    private Label votesLabel4;

    @FXML
    private Label votesLabel5;

    private ElectionSystem electionSystem;
    private List<Candidate> candidates;

    public void initElectionSystem(ElectionSystem electionSystem) {
        this.electionSystem = electionSystem;
        candidates = electionSystem.getCandidates();
        setupLabels();
        setupBarChart();
    }

    private void setupLabels() {
        Label[] nameLabels = { nameLabel1, nameLabel2, nameLabel3, nameLabel4, nameLabel5 };
        Label[] votesLabels = { votesLabel1, votesLabel2, votesLabel3, votesLabel4, votesLabel5 };

        if (candidates == null) return;

        for (int i = 0; i < nameLabels.length; i++) {
            if (i < candidates.size()) {
                Candidate candidate = candidates.get(i);
                nameLabels[i].setText("Name: " + candidate.getName());
                // Fetch and display votes for each candidate
                int votes = electionSystem.getVotesForCandidate(candidate.getName());
                votesLabels[i].setText("Votes: " + votes);
            } else {
                nameLabels[i].setText("");
                votesLabels[i].setText("");
            }
        }
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

    private void setupBarChart() {
        votesBarChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Votes");

        for (Candidate candidate : candidates) {
            int votes = electionSystem.getVotesForCandidate(candidate.getName());
            series.getData().add(new XYChart.Data<>(candidate.getName(), votes));
        }

        votesBarChart.getData().add(series);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateUserStatus();
    }

}
