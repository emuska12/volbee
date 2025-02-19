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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.java.election.ElectionSystem;
import main.java.election.Session;
import main.java.model.WindowUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;

public class GainHoneyController implements Initializable {


    @FXML
    private ImageView flower1;

    @FXML
    private ImageView flower10;

    @FXML
    private ImageView flower11;

    @FXML
    private ImageView flower12;

    @FXML
    private ImageView flower2;

    @FXML
    private ImageView flower3;

    @FXML
    private ImageView flower4;

    @FXML
    private ImageView flower5;

    @FXML
    private ImageView flower6;

    @FXML
    private ImageView flower7;

    @FXML
    private ImageView flower8;

    @FXML
    private ImageView flower9;

    @FXML
    private Label honeyAmount;

    @FXML
    private Hyperlink signOutLink,voteLink, actualitiesLink, resultsLink;

    @FXML
    private Button homeButton;

    @FXML
    private Label userStatusLabel;

    private ImageView[] allFlowers;
    private Random random = new Random();

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

    public void onFlowerClicked(ActionEvent event) {
        Session.addHoneyProduced(1);
        updateHoneyLabel();
        if (Session.isCandidate()) {
            electionSystem.updateCandidateHoney(Session.getUsername(), Session.getHoneyProduced());
        }
        ImageView clickedFlower = (ImageView) ((Button) event.getSource()).getGraphic();
        clickedFlower.setVisible(false);
        int visibleCount = 0;
        for (ImageView flower : allFlowers) {
            if (flower.isVisible()) visibleCount++;
        }

        while (visibleCount < 4) {
            int index = random.nextInt(allFlowers.length);
            if (!allFlowers[index].isVisible()) {
                allFlowers[index].setVisible(true);
                visibleCount++;
            }
        }
    }

    private void updateUserStatus() {
        String username = Session.getUsername();
        userStatusLabel.setText(username);
    }

    private void updateHoneyLabel() {
        int honey = Session.getHoneyProduced();
        honeyAmount.setText(String.valueOf(honey));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allFlowers = new ImageView[]{flower1, flower2, flower3, flower4, flower5, flower6, flower7, flower8, flower9, flower10, flower11, flower12};
        for (ImageView flower : allFlowers) {
            flower.setVisible(false);
        }
        for (int i = 0; i < 4; ) {
            int index = random.nextInt(allFlowers.length);
            if (!allFlowers[index].isVisible()) {
                allFlowers[index].setVisible(true);
                i++;
            }
        }
        updateUserStatus();
        updateHoneyLabel();
    }

}
