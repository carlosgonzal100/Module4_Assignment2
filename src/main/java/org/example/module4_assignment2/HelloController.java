package org.example.module4_assignment2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Controller for the Card 24 game screen (game-view.fxml).
 */
public class HelloController {

    // Suit names, spelled the same as in the image file names (e.g. "ace_of_spades.png")
    private static final String[] SUITS = {"clubs", "diamonds", "hearts", "spades"};

    // Card names as they appear in the image file names.
    // The index is the card's value: 1 = ace, 2-10 = face value, 11 = jack, 12 = queen, 13 = king.
    // Index 0 is left empty so each index lines up with its value.
    private static final String[] NAMES = {"", "ace", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "jack", "queen", "king"};

    // The four card slots from game-view.fxml (names must match the fx:id values)
    @FXML
    private ImageView card1_Image;
    @FXML
    private ImageView card2_Image;
    @FXML
    private ImageView card3_Image;
    @FXML
    private ImageView card4_Image;

    // Text box where the player types their expression
    @FXML
    private TextField user_Expression_Textbox;

    // Read-only text box next to the Find A Solution button, where the solution
    // (or "No solution possible") is shown
    @FXML
    private TextField solution_Box;

    // Values of the four cards currently shown (will be used by the Verify button)
    private final int[] cardValues = new int[4];

    /**
     * Runs automatically after the FXML is loaded. Deals the first four cards.
     */
    @FXML
    public void initialize() {
        dealCards();
    }

    /**
     * Called when the Refresh button is clicked. Deals a new set of four cards
     * and clears the expression and solution text boxes.
     */
    @FXML
    private void onRefreshClick() {
        dealCards();
        user_Expression_Textbox.clear();
        solution_Box.clear();
    }

    /**
     * Called when the Find A Solution button is clicked. Shows a solution for the
     * current four cards, or "No solution possible" if there isn't one.
     */
    @FXML
    private void onFindSolutionClick() {
        String solution = Card24Solver.findSolution(cardValues);
        if (solution != null) {
            solution_Box.setText(solution);
        } else {
            solution_Box.setText("No solution possible");
        }
    }

    /**
     * Called when the Verify button is clicked. Checks that the expression is valid,
     * uses each card's value exactly once, and equals 24. Shows the result in a dialog.
     */
    @FXML
    private void onVerifyClick() {
        String expression = user_Expression_Textbox.getText();

        // Check 1: the expression must be valid math (only numbers, + - * /, and parentheses)
        double result;
        try {
            result = ExpressionEvaluator.evaluate(expression);
        } catch (IllegalArgumentException e) {
            showResult(Alert.AlertType.ERROR, "Invalid Expression", e.getMessage());
            return;
        }

        // Check 2: the numbers used must be exactly the four card values, each used once.
        // Sorting both lists lets us compare them no matter what order the numbers were typed in.
        List<Integer> numbersUsed = ExpressionEvaluator.extractNumbers(expression);
        List<Integer> cardNumbers = new ArrayList<>();
        for (int value : cardValues) {
            cardNumbers.add(value);
        }
        Collections.sort(numbersUsed);
        Collections.sort(cardNumbers);

        if (!numbersUsed.equals(cardNumbers)) {
            showResult(Alert.AlertType.WARNING, "Incorrect",
                    "The numbers in your expression don't match the cards.\n"
                            + "Use each of these exactly once: " + Arrays.toString(cardValues));
            return;
        }

        // Check 3: the result must be 24 (same check the solver uses)
        if (Card24Solver.isTwentyFour(result)) {
            showResult(Alert.AlertType.INFORMATION, "Correct!", "Correct! " + expression + " = 24");
            // After the dialog is closed, start a new round with new cards and an empty text box
            onRefreshClick();
        } else {
            showResult(Alert.AlertType.WARNING, "Incorrect",
                    "Your expression equals " + String.format("%.2f", result) + ", not 24.");
        }
    }

    /**
     * Shows a pop-up dialog box with the result of the Verify check.
     */
    private void showResult(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Shuffles a 52-card deck, takes the top four cards, shows their images,
     * and stores their values in cardValues.
     */
    private void dealCards() {
        // Build the deck as the numbers 0-51, one number per card
        List<Integer> deck = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            deck.add(i);
        }
        Collections.shuffle(deck);

        ImageView[] slots = {card1_Image, card2_Image, card3_Image, card4_Image};

        // Take the first four cards of the shuffled deck (no duplicates possible)
        for (int i = 0; i < 4; i++) {
            int card = deck.get(i);
            int value = card % 13 + 1;          // 1-13 (ace through king)
            String suit = SUITS[card / 13];     // 0-12 clubs, 13-25 diamonds, ...

            cardValues[i] = value;

            String fileName = "cards/" + NAMES[value] + "_of_" + suit + ".png";
            slots[i].setImage(new Image(getClass().getResource(fileName).toExternalForm()));
        }
    }
}
