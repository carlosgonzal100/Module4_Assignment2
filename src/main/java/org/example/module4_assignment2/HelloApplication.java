package org.example.module4_assignment2;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-view.fxml"));
        Parent root = fxmlLoader.load();

        // Size the layout was designed at in Scene Builder (root pane's pref size)
        double designWidth = 600;
        double designHeight = 400;

        Group group = new Group(root);
        StackPane wrapper = new StackPane(group);   // keeps the layout centered
        Scene scene = new Scene(wrapper, designWidth, designHeight);

        // Scale the whole layout to fit the window, keeping its proportions
        group.scaleXProperty().bind(Bindings.min(
                scene.widthProperty().divide(designWidth),
                scene.heightProperty().divide(designHeight)));
        group.scaleYProperty().bind(group.scaleXProperty());

        // Casino-style look (green felt, gold buttons, fonts) from casino.css
        scene.getStylesheets().add(HelloApplication.class.getResource("casino.css").toExternalForm());

        stage.setTitle("Card 24");
        stage.setScene(scene);
        stage.show();
    }
}
