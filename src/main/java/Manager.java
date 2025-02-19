package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.gui.GUI;
import main.java.election.ElectionSystem;

public class Manager extends Application {
    private ElectionSystem electionSystem;

    @Override
    public void start(Stage primaryStage) throws Exception {
        electionSystem = new ElectionSystem();
        GUI gui = new GUI(electionSystem);
        gui.loadFirstScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
