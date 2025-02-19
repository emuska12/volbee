package main.java.model;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class WindowUtils {

    public static void applyWindowFeatures(Stage stage, Parent root) {
        final double[] x = new double[1];
        final double[] y = new double[1];

        root.setOnMousePressed(event -> {
            x[0] = event.getSceneX();
            y[0] = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x[0]);
            stage.setY(event.getScreenY() - y[0]);
            stage.setOpacity(0.8);
        });

        root.setOnMouseReleased(event -> stage.setOpacity(1.0));
    }
}