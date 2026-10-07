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
 * Note:
 * got my playing card images here:
 * https://opengameart.org/content/playing-cards-vector-png
 */

/**
 * AI Usage disclosure:
 * This project was made in assistance with claude.ai. Here are the prompts
 * that i used:
 *
 * (1) used this prompt to help adjust the GUI to the size of the window,
 * even when the window is resized, the GUI and things in it matches the
 * windows size. The result of this prompt gave me code for my HelloApplication.java
 * file, which I then modified to fit my needs and caused the GUI to resize when the
 * user resized the window. The prompt was:
 *
 * "i am working on this module4_assignment2 thing and i made a whole GUI for my 24
 * card game, but the view i made in scene builder dousnt adjust to the size of the
 * window, how do i do this?"
 *
 * (2) used this prompt to help me implement the card deck images that i dowloaded, give each
 * card a value and choose 4 at random and put them into the GUI display. Claude ended up putting
 * assigning each card a value, put them into a collection, shuffled the cards in the collection, and then
 * choose 4 random cards from the shuffled collection and display them in the GUI. The prompt was:
 *
 * "ok now can you help me implement these card pictures into my assignment, i need the program to display
 * 4 random cards that were chosen from the deck with each one given a value. the ace = 1, 2 - 10 are their
 * face values, jack = 11, queen = 12, king = 13."
 *
 * changes made: fixed up my game-view.fxml file. Then implemented the card deck logic and display
 * in the HelloController.java file, were 4 random cards are chosen from the deck and then displayed.
 *
 * (3) used this prompt to help me implement the verify button. With the help of claude code
 * ,claude helped me have the verify button check if the 4 numbers in the expression are the ones
 * displayed in the GUI, and if they are, then it checks if the expression is valid and equals 24. one
 * change was made to the game-view.fxml file to connect the verify button to the onVerifyClick() method
 * in the HelloController.java file. Also a new class called ExpressionEvaluator.Java was made to assist
 * in evaluating the users mathematical expression and keeping classes like the HelloController.java class clean.
 * it also gave me a showResult() method in the HelloController.java Class to show the users result for their expression.
 * The prompt was:
 *
 * "help me with the verify button. The verify button checks if the users input amounts to 24, by using the 4 cards
 * given on the display and by using multiplication, addition, subtraction or division. The user can also use
 * parenthesis to group numbers and operators and they can only use each number once in the expression."
 *
 * (4) used this prompt to help be implement the find a solution button. the button when
 * pressed shows the solution to the problem using the 4 cards, addition, subtraction,
 * multiplication, division and parethesis. it is shown in the text field next to the button.
 * the result gave me a class called card24Solver.java that has a method called findSolution()
 * that takes in the 4 card values and returns a string with the solution. it also connected
 * this method to the find a solution button. The prompt was:
 *
 * "i need help with the find a solution button. this button must find a solution to reaching
 * the value 24 by using the 4 displayed cards and their value, parethesis, multiplication,
 * addition, subtraction and division. if there is a solution, display the solution to the
 * problem in the solution textfield next to the find a solution button, which cant be tampered
 * with at all. if there isnt a solution, display "No solution possible". A succesful verification
 * and a refresh resets this text field as well."
 *
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
