package main.java.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.election.Candidate;
import main.java.election.ElectionSystem;
import main.java.election.Session;
import main.java.model.WindowUtils;
import main.java.etnities.Bee;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class VoteController implements Initializable {

    @FXML
    private Hyperlink actualitiesLink;

    @FXML
    private Hyperlink candidatesLink;

    @FXML
    private ChoiceBox<?> choiceBox1;

    @FXML
    private ChoiceBox<?> choiceBox2;

    @FXML
    private ChoiceBox<?> choiceBox3;

    @FXML
    private ChoiceBox<?> choiceBox4;

    @FXML
    private ChoiceBox<?> choiceBox5;

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
    private Button submitButton;

    @FXML
    private Label warningLabel;

    @FXML
    private Label userStatusLabel;

    @FXML
    private Hyperlink voteLink;

    private ElectionSystem electionSystem;

    public void initElectionSystem(ElectionSystem electionSystem) {
        this.electionSystem = electionSystem;
        populateChoiceBoxes();
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
            Stage stage = (Stage) candidatesLink.getScene().getWindow();
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

    private boolean areAllChoicesFilled() {
        ChoiceBox<String>[] choiceBoxes = new ChoiceBox[] {choiceBox1, choiceBox2, choiceBox3, choiceBox4, choiceBox5};
        for (ChoiceBox<String> box : choiceBoxes) {
            if (!box.isDisabled() && (box.getValue() == null || box.getValue().trim().isEmpty())) {
                return false;
            }
        }
        return true;
    }

    private boolean areDuplicateSelectionsMade() {
        Set<String> seenChoices = new HashSet<>();
        ChoiceBox<String>[] choiceBoxes = new ChoiceBox[] {choiceBox1, choiceBox2, choiceBox3, choiceBox4, choiceBox5};
        for (ChoiceBox<String> box : choiceBoxes) {
            if (!box.isDisabled() && box.getValue() != null) {
                if (!seenChoices.add(box.getValue())) {
                    return true;  // Duplicate found
                }
            }
        }
        return false;
    }


    @FXML
    private void onSubmitButtonClicked() {
        if (electionSystem.hasUserVoted(Session.getUsername())) {
            warningLabel.setText("You can only submit your preferences once");
            warningLabel.setVisible(true);
        }
        else if (!areAllChoicesFilled()) {
            warningLabel.setText("Please fill out all choices");
            warningLabel.setVisible(true);
        }
        else if (areDuplicateSelectionsMade()) {
            warningLabel.setText("Please avoid any duplicates in your preferences");
            warningLabel.setVisible(true);
        }
        else {
            warningLabel.setText("You have submited your preferences successfully");
            warningLabel.setVisible(true);
            Session.setHasVoted(true);
            electionSystem.markUserAsVoted(Session.getUsername());
            List<String> preferences = getPreferences();
            electionSystem.vote(electionSystem.getCurrentUser(), preferences);
            // Proceed with the submission logic
        }
    }

    private List<String> getPreferences() {
        List<String> preferences = new ArrayList<>();
        ChoiceBox<String>[] choiceBoxes = new ChoiceBox[] {choiceBox1, choiceBox2, choiceBox3, choiceBox4, choiceBox5};
        for (ChoiceBox<String> box : choiceBoxes) {
            if (!box.isDisabled() && box.getValue() != null) {
                preferences.add(box.getValue());
            }
        }
        return preferences;
    }

    private void populateChoiceBoxes() {
        if (electionSystem == null) return;
        Label[] nameLabels = { nameLabel1, nameLabel2, nameLabel3, nameLabel4, nameLabel5 };
        List<Candidate> candidates = electionSystem.getCandidates();
        for (int i = 0; i < 5; i++) {
            if (candidates != null && i < candidates.size()) {
                nameLabels[i].setText(candidates.get(i).getName());
            } else {
                nameLabels[i].setText("");
            }
        }
        List<String> candidateNames = candidates.stream()
                .map(Candidate::getName)
                .collect(Collectors.toList());

        // Populate each ChoiceBox and enable or disable them based on the number of candidates
        ChoiceBox<String>[] choiceBoxes = new ChoiceBox[] { choiceBox1, choiceBox2, choiceBox3, choiceBox4, choiceBox5 };
        for (int i = 0; i < choiceBoxes.length; i++) {
            if (i < candidateNames.size()) {
                choiceBoxes[i].setItems(FXCollections.observableArrayList(candidateNames));
                choiceBoxes[i].setDisable(false);  // Enable the choice box
            } else {
                choiceBoxes[i].setItems(FXCollections.observableArrayList());  // Set empty
                choiceBoxes[i].setDisable(true);  // Disable the choice box
            }
        }
    }

    private void updateUserStatus() {
        String username = Session.getUsername();
        userStatusLabel.setText(username);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateUserStatus();
        populateChoiceBoxes();
    }

}
