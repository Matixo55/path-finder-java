package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Pane root = new Pane();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), primaryScreenBounds.getWidth() * 0.75, primaryScreenBounds.getHeight() * 0.75);

        stage.setTitle("Path finder");
        stage.setScene(scene);
        stage.setMinHeight(700);
        stage.setMinWidth(900);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}