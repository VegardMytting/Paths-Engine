package no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action.ActionFactory;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Link;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Passage;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal.Goal;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal.GoalFactory;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.scenes.GameScene;

/**
 * Represents a controller for {@link GameScene}.
 * <p>
 * The controller is responsible for handling events in the scene. Events such
 * as:
 * <ul>
 * <li>Following links</li>
 * <li>Displays dialogs about the game's state</li>
 * </ul>
 * </p>
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class GameSceneController {
  private MainMenuAppController mainMenuAppController;
  private GameScene gameScene;
  private Random random = new Random();

  /**
   * Constructs an instance of the GameSceneController.
   *
   * @param gameScene             the scene this controller is responsible for.
   * @param mainMenuAppController the main application controller.
   */
  public GameSceneController(GameScene gameScene, MainMenuAppController mainMenuAppController) {
    this.gameScene = gameScene;
    this.mainMenuAppController = mainMenuAppController;
  }

  /**
   * Updates the player for changes, and cehcks for vital statuses.
   * 
   * <p>
   * The method preforms the following actions:
   * <ul>
   * <li>Updates the current link-buttons.</li>
   * <li>Updates the current passage.</li>
   * <li>updates the player.</li>
   * <li>Checks if the player is alive.</li>
   * <li>Checks if all the goals are fullfilled.</li>
   * </ul>
   * </p>
   *
   * @param link the link to follow.
   */
  private void followLink(Link link) {
    Passage passage = this.mainMenuAppController.getGame().go(link);
    this.gameScene.createLinkButtons(passage);
    this.gameScene.updatePassage(passage);
    this.mainMenuAppController.setCurrentPassage(passage);
    this.gameScene.updatePlayer();
    this.victoryDialog();
    this.deadDialog();
  }

  /**
   * Allows mini-games to the game with a 1/10 chance of finding a mini-game.
   * 
   * <p>
   * This method acts like the {@link GameSceneController#followLink(Link link)
   * followLink-method}, only it impliments the concept of mini-games.
   * </p>
   *
   * @param link the link to follow;
   */
  public void followLinkMiniGame(Link link) {
    if (this.mainMenuAppController.getMiniGameStatus()) {
      int miniGame = this.random.nextInt(30);
      switch (miniGame) {
        case 0:
          this.diceRollMiniGame(link);
          break;
        case 1:
          this.cupGameMiniGame(link);
          break;
        case 2:
          this.mindReaderMiniGame(link);
          break;
        default:
          this.followLink(link);
      }
    } else {
      this.followLink(link);
    }
  }

  /**
   * 
   * <p>
   * If the player chooses wrong, the player will lose as much health as the dice
   * roll is showing.
   * </p>
   *
   * @param link the link to follow.
   */
  private void diceRollMiniGame(Link link) {
    Alert miniGameAlert = new Alert(Alert.AlertType.INFORMATION);
    String information = "Information";
    miniGameAlert.setTitle(information);
    miniGameAlert.setHeaderText("You have discovered a mini-game!");
    miniGameAlert.setContentText("""
        You must roll a 4 or higher on this dice-roll to go through!
        If you lose, you will lose as much health as the dice rolls.
          """);
    Optional<ButtonType> result = miniGameAlert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      int diceRoll = this.random.nextInt(6);
      if (diceRoll >= 3) {
        Alert diceRollResultAlert = new Alert(Alert.AlertType.INFORMATION);
        diceRollResultAlert.setTitle("Mini Game");
        diceRollResultAlert.setHeaderText("You rolled a " + (diceRoll + 1) + "!");
        diceRollResultAlert.setContentText(
            "You beat the mini-game and can now continue your adventure!");
        diceRollResultAlert.showAndWait();

        this.followLink(link);
      } else {
        Alert diceRollResultAlert = new Alert(Alert.AlertType.INFORMATION);
        diceRollResultAlert.setTitle("Mini Game");
        diceRollResultAlert.setHeaderText("You rolled a " + (diceRoll + 1) + "!");
        diceRollResultAlert.setContentText(
            "You did not beat the mini-game.");
        diceRollResultAlert.showAndWait();

        ActionFactory.createAction("health action", -(diceRoll + 1))
            .execute(this.mainMenuAppController.getGame().getPlayer());
        this.gameScene.updatePlayer();
        this.deadDialog();
      }
    }
  }

  /**
   * Creates a mini-game alert.
   * 
   * <p>
   * This mini-game is a cup game. The user must choose the right "cup" to
   * be allowed to go to the next passage in the story. If not, the current
   * passage will not update.
   * </p>
   * 
   * <p>
   * If the player chooses wrong, the player will lose 10 health points.
   * </p>
   *
   * @param link the link to follow.
   */
  private void cupGameMiniGame(Link link) {
    Alert miniGameAlert = new Alert(Alert.AlertType.INFORMATION);
    miniGameAlert.setTitle("Information");
    miniGameAlert.setHeaderText("You have discovered a mini-game!");
    miniGameAlert.setContentText("""
        You must choose the right button to continue your adventure!
        If you lose, you will lose 10 health points.
          """);

    Optional<ButtonType> result = miniGameAlert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      ButtonType cupOneButton = new ButtonType("Cup 1");
      ButtonType cupTwoButton = new ButtonType("Cup 2");
      ButtonType cupThreeButton = new ButtonType("Cup 3");
      Alert cupGameAlert = new Alert(Alert.AlertType.INFORMATION,
          "Please choose (and press) one of the three buttons.",
          cupOneButton, cupTwoButton, cupThreeButton);
      cupGameAlert.setTitle("Inromation");
      cupGameAlert.setHeaderText("Welcome To Cup-Game!");

      Optional<ButtonType> gameResult = cupGameAlert.showAndWait();
      if (gameResult.isPresent()) {
        int cupGame = random.nextInt(3);
        if (cupGame == 1) {
          Alert cupGameResultAlert = new Alert(Alert.AlertType.INFORMATION);
          cupGameResultAlert.setTitle("Mini Game");
          cupGameResultAlert.setHeaderText("You chose right!");
          cupGameResultAlert.setContentText(
              "You beat the mini-game and can now continue your adventure!");
          cupGameResultAlert.showAndWait();

          this.followLink(link);
        } else {
          Alert diceRollAlert = new Alert(Alert.AlertType.INFORMATION);
          diceRollAlert.setTitle("Mini Game");
          diceRollAlert.setHeaderText("You chose wrong!");
          diceRollAlert.setContentText(
              "You did not beat the mini-game.");
          diceRollAlert.showAndWait();

          ActionFactory.createAction("health action", -10)
              .execute(this.mainMenuAppController.getGame().getPlayer());
          this.gameScene.updatePlayer();
          this.deadDialog();
        }
      }
    }
  }

  /**
   * Creates a mini-game alert.
   * 
   * <p>
   * This mini-game is a mind-reader game. The user must guess the right number,
   * that is a whole positive number between 1 and 10, to be allowed to go to the
   * next passage. If not, the current passage will not update.
   * </p>
   * 
   * <p>
   * If the player chooses wrong, the player will lose as much health as the right
   * answer would have been.
   * </p>
   *
   * @param link the link to follow.
   */
  private void mindReaderMiniGame(Link link) {
    Alert miniGameAlert = new Alert(Alert.AlertType.INFORMATION);
    miniGameAlert.setTitle("Information");
    miniGameAlert.setHeaderText("You have discovered a mini-game!");
    miniGameAlert.setContentText(
        """
            You must choose the right number between 1 and 10 to continue you adventure!
            If you lose, you will lose as much health as the right answer would have been.
              """);

    Optional<ButtonType> result = miniGameAlert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      TextInputDialog mindReaderDialog = new TextInputDialog();
      mindReaderDialog.setTitle("Mini Game");
      mindReaderDialog.setHeaderText("Welcome to Mind-Reader Game");
      mindReaderDialog.setContentText("Please enter a whole positive number between 1 and 10.");

      TextField numberTextField = mindReaderDialog.getEditor();
      numberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
        try {
          if (newValue.length() > 0) {
            Integer.parseInt(newValue);
          }
        } catch (NumberFormatException nfe) {
          numberTextField.setText(oldValue);
        }
      });

      Optional<String> gameResult = mindReaderDialog.showAndWait();
      if (gameResult.isPresent()) {
        int number;
        try {
          number = Integer.parseInt(gameResult.get());
        } catch (NumberFormatException nfe) {
          number = 0;
        }

        int mindReader = random.nextInt(10);
        if (number != (mindReader + 1)) {
          Alert mindReaderResultAlert = new Alert(Alert.AlertType.INFORMATION);
          mindReaderResultAlert.setTitle("Mini Game");
          mindReaderResultAlert.setHeaderText("You chose wrong!");
          mindReaderResultAlert.setContentText(
              "You did not beat the mini-game, and lost " + (mindReader + 1) + " health points.");
          mindReaderResultAlert.showAndWait();

          ActionFactory.createAction("health action", -(mindReader + 1))
              .execute(this.mainMenuAppController.getGame().getPlayer());
          this.gameScene.updatePlayer();
          this.deadDialog();
        } else {
          Alert mindReaderResultAlert = new Alert(Alert.AlertType.INFORMATION);
          mindReaderResultAlert.setTitle("Mini Game");
          mindReaderResultAlert.setHeaderText("You chose right!");
          mindReaderResultAlert.setContentText(
              "You beat the mini-fame and can now continye your adventure!");
          mindReaderResultAlert.showAndWait();

          this.followLink(link);
        }
      }
    }
  }

  /**
   * Checks the status of the player on if the player has fulfilled all their
   * goals.
   * 
   * <p>
   * Checks for the player's health, gold, score and inventory goals.
   * </p>
   *
   * @return {@code true} if all goals are fulfilled,
   *         {@code false}, if all the goals are not fulfilled.
   */
  public boolean checkGoalStatus() {
    return this.mainMenuAppController.getSpecificGoal("health goal")
        .isFulfilled(this.mainMenuAppController.getGame().getPlayer())
        && this.mainMenuAppController.getSpecificGoal("gold goal")
            .isFulfilled(this.mainMenuAppController.getGame().getPlayer())
        && this.mainMenuAppController.getSpecificGoal("score goal")
            .isFulfilled(this.mainMenuAppController.getGame().getPlayer())
        && this.mainMenuAppController.getSpecificGoal("inventory goal")
            .isFulfilled(this.mainMenuAppController.getGame().getPlayer());
  }

  /**
   * Displays a dialog when the player has achived all their goals.
   * 
   * <p>
   * The Player can click the 'Main Menu' button to return to the main menu,
   * and the 'Free Play' button to continue playing the game.
   * </p>
   */
  public void victoryDialog() {
    if (this.checkGoalStatus() && this.mainMenuAppController.getGameCheck()) {
      ButtonType mainMenuButton = new ButtonType("Main Menu");
      ButtonType freePlayButton = new ButtonType("Free Play");
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          """
              Click the 'Main Menu' button to return to the main menu.
              Click the 'Free Play' button to continue your adventure.
                """, freePlayButton, mainMenuButton);
      alert.setTitle("Confirmation");
      alert.setHeaderText("Congratulations! You have won the game!");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.isPresent() && result.get() == mainMenuButton) {
        this.mainMenuAppController.clearGame();
        this.mainMenuAppController.switchScene(this.mainMenuAppController.getMainMenuScene());
      } else {
        this.mainMenuAppController.setGameCheck(false);
        this.freePlayGame();
      }
    }
  }

  /**
   * Checks if the player's health is zero.
   *
   * @return {@code true} if the health is zero, and
   *         {@code false} if the player's health is greater than zero
   */
  public boolean checkHealthStatus() {
    return this.mainMenuAppController.getGame().getPlayer().getHealth() == 0;
  }

  /**
   * Displays a dialog when the player has died.
   * 
   * <p>
   * The player can click the 'respawn' button to restart the game,
   * and the 'Main Menu' button to return to the main menu.
   * </p>
   */
  public void deadDialog() {
    if (this.checkHealthStatus()) {
      ButtonType respawnButton = new ButtonType("Respawn");
      ButtonType mainMenuButton = new ButtonType("Main Menu");
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          """
              Click the 'Respawn' button to restart the game.
              Click the 'Main Mneu' button to return to the main menu.
                """, respawnButton, mainMenuButton);
      alert.setTitle("Confirmation");
      alert.setHeaderText("Game Over! You have died!");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.isPresent() && result.get() == mainMenuButton) {
        this.mainMenuAppController.clearGame();
        this.mainMenuAppController.switchScene(this.mainMenuAppController.getMainMenuScene());
      } else {
        this.resetGame();
      }
    }
  }

  /**
   * Resets the game to its start game state.
   */
  public void resetGame() {
    this.gameScene.createLinkButtons(this.mainMenuAppController
        .getGame().begin());
    this.gameScene.updatePassage(this.mainMenuAppController
        .getGame().begin());
    this.mainMenuAppController.setCurrentPassage(null);
    this.mainMenuAppController.getGame().getPlayer()
        .setName(this.mainMenuAppController.getStartPlayer().getName());
    this.mainMenuAppController.getGame().getPlayer()
        .setHealth(this.mainMenuAppController.getStartPlayer().getHealth());
    this.mainMenuAppController.getGame().getPlayer()
        .setGold(this.mainMenuAppController.getStartPlayer().getGold());
    this.mainMenuAppController.getGame().getPlayer()
        .setScore(this.mainMenuAppController.getStartPlayer().getScore());
    this.mainMenuAppController.getGame().getPlayer()
        .setInventory(this.mainMenuAppController.getStartPlayer().getInventory());
    this.mainMenuAppController.getGoals()
        .set(0, this.mainMenuAppController.getSpecificStartGoal("health goal"));
    this.mainMenuAppController.getGoals()
        .set(1, this.mainMenuAppController.getSpecificStartGoal("gold goal"));
    this.mainMenuAppController.getGoals()
        .set(2, this.mainMenuAppController.getSpecificStartGoal("score goal"));
    this.mainMenuAppController.getGoals()
        .set(3, this.mainMenuAppController.getSpecificStartGoal("inventory goal"));
    this.gameScene.updatePlayer();
  }

  /**
   * Starts the game with what the 'current passage' was last.
   */
  public void continueGame() {
    this.mainMenuAppController.switchScene(this.gameScene.createGameScene());
    this.gameScene.createLinkButtons(this.mainMenuAppController.getCurrentPassage());
    this.gameScene.updatePassage(this.mainMenuAppController.getCurrentPassage());
    this.gameScene.updatePlayer();
  }

  /**
   * Makes the game to a 'Free Play' version, it is impossible to win in this
   * state.
   */
  public void freePlayGame() {
    try {
      final List<Goal> unachivableGoals = new LinkedList<>();
      GoalFactory.createGoal("health goal", null);
      GoalFactory.createGoal("gold goal", null);
      GoalFactory.createGoal("score goal", null);
      GoalFactory.createGoal("inventory goal", null);

      this.mainMenuAppController.setGoals(unachivableGoals);
      this.gameScene.updatePlayer();
    } catch (IllegalArgumentException iae) {
      iae.getMessage();
    }
  }
}