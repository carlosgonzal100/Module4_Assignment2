Title: CSC311Module4Assignment2

Course: CSC311 Advanced Programming

Professor: Moaath Alrajab

Author: Carlos Gonzalez with the assistance of CLAUDE

Description: A card game for my CSC311 Advanced Programming class called Card 24. The user is given 4 random cards and
must use those 4 random cards to generate a mathematical expression that equals 24. The user can only use the multiplication,
division, addition, subtraction, and parenthesis operators. Also, they can only use each card once in their expression. Once they
have written their expression, the user uses the verify button to submit their answer. The verify button uses the ExpressionEvaluator.java
class to check and calculate the value of the user's expression. It also checks for any error such as an unintended character like a letter
or a formatting error such as a misplaced parenthesis. It will display these errors in a dialog box and will display whether the user's answer is 
correct or wrong. If the answer is correct, the cards are refreshed, and all fields are cleared. The Find an Answer button uses the Card24Solver.java 
class to make every possible combination using the 4 cards and the allowed mathematical operators; each expression is given to the ExpressionEvaluator.java 
class, and if the expression is correct, the answer is printed next to the Find an Answer button. The refresh button refreshes the 4 dealt cards.

  Note:
  Got my playing card images here:
  https://opengameart.org/content/playing-cards-vector-png
 


  AI Usage disclosure:
  This project was made with assistance from claude.ai. Here are the prompts
  that I used:
 
  (1) used this prompt to help adjust the GUI to the size of the window,
  even when the window is resized, the GUI and things in it match the
  window's size. The result of this prompt gave me code for my HelloApplication.java
  file, which I then modified to fit my needs and caused the GUI to resize when the
  user resized the window. The prompt was:
 
  "I am working on this module4_assignment2 thing, and I made a whole GUI for my 24
  card game, but the view I made in Scene Builder doesn't adjust to the size of the
  window. How do I do this?"
 
  (2) used this prompt to help me implement the card deck images that I downloaded, give each
  card a value, choose 4 at random, and put them into the GUI display. Claude ended up putting
  assigning each card a value, putting them into a collection, shuffling the cards in the collection, and then
  choosing 4 random cards from the shuffled collection and displaying them in the GUI. The prompt was:
 
  "ok now can you help me implement these card pictures into my assignment, i need the program to display
  4 random cards that were chosen from the deck, with each one given a value. the ace = 1, 2 - 10 are their
  face values, jack = 11, queen = 12, king = 13."
 
  changes made: fixed up my game-view.fxml file. Then implemented the card deck logic and display
  in the HelloController.java file, where 4 random cards are chosen from the deck and then displayed.
 
  (3) used this prompt to help me implement the verify button. With the help of Claude Code
 , Claude helped me have the Verify button check if the 4 numbers in the expression are the ones
  displayed in the GUI, and if they are, then it checks if the expression is valid and equals 24. One
  change was made to the game-view.fxml file to connect the Verify button to the onVerifyClick() method
  in the HelloController.java file. Also, a new class called ExpressionEvaluator.Java was made to assist
  in evaluating the user's mathematical expression and keeping classes like the HelloController.java class clean.
  It also gave me a showResult() method in the HelloController.java Class to show the user's result for their expression.
  The prompt was:
 
  "help me with the verify button. The verify button checks if the users input amounts to 24, by using the 4 cards
  given on the display and by using multiplication, addition, subtraction or division. The user can also use
  parenthesis to group numbers and operators and they can only use each number once in the expression."
 
 (4) used this prompt to help implement the find a solution button. The button, when
  pressed, shows the solution to the problem using the 4 cards, addition, subtraction,
  multiplication, division, and parentheses. It is shown in the text field next to the button.
  the result gave me a class called card24Solver.java that has a method called findSolution()
  that takes in the 4 card values and returns a string with the solution. it also connected
  this method to the Find a Solution button. The prompt was:
 
  "i need help with the find a solution button. this button must find a solution to reaching
  the value 24 by using the 4 displayed cards and their value, parethesis, multiplication,
  addition, subtraction and division. if there is a solution, display the solution to the
  problem in the solution textfield next to the find a solution button, which cant be tampered
  with at all. if there isnt a solution, display "No solution possible". A succesful verification
  and a refresh resets this text field as well."
 
  (5) used this prompt with CLAUDE to add a CSS sheet. This CSS sheet will change the
  button shapes, change background colors, text colros, text fonts, and the looks of the app
  to make the whole thing look like a casino game. I also told it ti change the title of the
  window, as it still said "Hello!". A simple prompt was given to get the app to
  look like a casino game. The prompt was:
 
  "change the window title to Card 24, and use a CS styling sheet to change up
  the fonts of the game, make it look casino like"
