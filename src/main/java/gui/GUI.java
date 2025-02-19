package main.java.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.model.WindowUtils;
import main.java.election.ElectionSystem;

public class GUI {
    private ElectionSystem electionSystem;

    public GUI(ElectionSystem electionSystem) {
        this.electionSystem = electionSystem;
    }

    public void loadFirstScene(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/forms/FirstScene.fxml"));
        Parent root = loader.load();

        // Pass the ElectionSystem instance to the controller
        FirstPageController controller = loader.getController();
        controller.initElectionSystem(electionSystem);

        Scene scene = new Scene(root, 800, 600);
        WindowUtils.applyWindowFeatures(primaryStage, root);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
