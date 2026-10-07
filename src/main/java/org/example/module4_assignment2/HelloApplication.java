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

/**
 * AI Usage disclosure:
 * This project was made in assistance with claude.ai. Here are the prompts
 * that i used:
 *
 * used this prompt to help adjust the GUI to the size of the window,
 * even when the window is resized, the GUI and things in it matches the
 * windows size. The result of this prompt gave me code for my HelloApplication.java
 * file, which I then modified to fit my needs and caused the GUI to resize when the
 * user resized the window. The prompt was:
 *
 * "i am working on this module4_assignment2 thing and i made a whole GUI for my 24
 * card game, but the view i made in scene builder dousnt adjust to the size of the
 * window, how do i do this?"
 */
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

        stage.setTitle("24 Card Game");
        stage.setScene(scene);
        stage.show();
    }
}
