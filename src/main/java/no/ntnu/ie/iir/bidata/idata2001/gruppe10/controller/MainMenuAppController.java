package no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller;

import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Passage;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal.Goal;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.logic.Game;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.logic.Story;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.scenes.MainMenuApp;

/**
 * Represents a controller for the application.
 * <p>
 * The controller is responsible for maintaining information for creating and
 * running the application.
 * </p>
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class MainMenuAppController {
  private Stage primaryStage;
  private MainMenuApp mainMenuApp;
  private Player player;
  private Story story;
  private List<Goal> goals;
  private Game game;
  private Player startPlayer;
  private List<Goal> startGoals;
  private Passage currentPassage;
  private boolean gameCheck = true;
  private boolean miniGameStatus;

  /**
   * Constructs an instance of the MainMenuAppController.
   *
   * @param mainMenuApp the application this controller is responsible for.
   */
  public MainMenuAppController(MainMenuApp mainMenuApp, Stage primaryStage) {
    this.mainMenuApp = mainMenuApp;
    this.primaryStage = primaryStage;
  }

  /**
   * Switches scenes in the application.
   */
  public void switchScene(Scene scene) {
    this.primaryStage.setScene(scene);
  }

  /**
   * Returns the main menu scene.
   *
   * @return the main menu scene.
   */
  public Scene getMainMenuScene() {
    return this.mainMenuApp.createMainMenuScene();
  }

  /**
   * Sets the player as a player.
   *
   * @param player the player to be set.
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Returns the player.
   *
   * @return the player.
   */
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Creates a player with it's start values.
   */
  public void createStartPlayer() {
    this.startPlayer = new Player.Builder()
        .setName(this.player.getName())
        .setHealth(this.player.getHealth())
        .setGold(this.player.getGold())
        .setScore(this.player.getScore())
        .addToInventory(this.player.getInventory())
        .build();
  }

  /**
   * Returns the player with the start values.
   *
   * @return the player with the start values.
   */
  public Player getStartPlayer() {
    return this.startPlayer;
  }

  /**
   * Sets a start value.
   */
  public void setStartGoals() {
    this.startGoals = this.goals;
  }

  /**
   * Return a list of the start values.
   *
   * @return a list of the start values.
   */
  public List<Goal> getStartGoal() {
    return this.startGoals;
  }

  /**
   * Returns a specific goal.
   *
   * @param goalType the specific goal to get.
   * @return a specific goal.
   * @throws IllegalArgumentException if the goal type is not valid.
   */
  public Goal getSpecificStartGoal(String goalType) {
    switch (goalType) {
      case "health goal":
        return this.startGoals.get(0);
      case "gold goal":
        return this.startGoals.get(1);
      case "score goal":
        return this.startGoals.get(2);
      case "inventory goal":
        return this.startGoals.get(3);
      default:
        throw new IllegalArgumentException(goalType + " is not a valid goal-type");
    }
  }

  /**
   * Sets the story as a story.
   *
   * @param story the story to set.
   */
  public void setStory(Story story) {
    this.story = story;
  }

  /**
   * Returns the story.
   *
   * @return the story.
   */
  public Story getStory() {
    return this.story;
  }

  /**
   * Sets the goal as a goal.
   *
   * @param goals the goal to set.
   */
  public void setGoals(List<Goal> goals) {
    this.goals = goals;
  }

  /**
   * Returns a list og goals.
   *
   * @return a list of goals.
   */
  public List<Goal> getGoals() {
    return this.goals;
  }

  /**
   * Returns the specified goal.
   *
   * @param goalType the specific goal to get.
   * @return the specified goal.
   * @throws IllegalArgumentException if the goal type is not valid
   */
  public Goal getSpecificGoal(String goalType) {
    switch (goalType) {
      case "health goal":
        return this.goals.get(0);
      case "gold goal":
        return this.goals.get(1);
      case "score goal":
        return this.goals.get(2);
      case "inventory goal":
        return this.goals.get(3);
      default:
        throw new IllegalArgumentException(goalType + " is not a valid goal-type");
    }
  }

  /**
   * Sets the game as a game.
   *
   * @param game the game to set.
   */
  public void setGame(Game game) {
    this.game = game;
  }

  /**
   * Returns the game.
   *
   * @return the game.
   */
  public Game getGame() {
    return this.game;
  }

  /**
   * Sets the current passage as a passage.
   *
   * @param passage the passage to set.
   */
  public void setCurrentPassage(Passage passage) {
    this.currentPassage = passage;
  }

  /**
   * Returns the current passage.
   *
   * @return the current passage.
   */
  public Passage getCurrentPassage() {
    return this.currentPassage;
  }

  /**
   * Returns the mainMenuApp.
   *
   * @return the mainmenuApp.
   */
  public MainMenuApp getMainMenuApp() {
    return this.mainMenuApp;
  }

  /**
   * Clears the game.
   */
  public void clearGame() {
    this.setStory(null);
    this.setPlayer(null);
    this.setGoals(null);
  }

  /**
   * Sets the game check as a boolean.
   *
   * @param status the status of the game check.
   */
  public void setGameCheck(boolean status) {
    this.gameCheck = status;
  }

  /**
   * Returns the game check.
   *
   * @return the game check.
   */
  public boolean getGameCheck() {
    return this.gameCheck;
  }

  /**
   * Sets the mini game status as a boolean.
   *
   * @param status the status of the mini game status.
   */
  public void setMiniGameStatus(boolean status) {
    this.miniGameStatus = status;
  }

  /**
   * Returns the mini game status.
   *
   * @return the mini game status.
   */
  public boolean getMiniGameStatus() {
    return this.miniGameStatus;
  }

  /**
   * Exits the application with a confirmation alert.
   */
  public void doExitApplication() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Exit the Application");
    alert.setContentText("Are you sure you want to exit the application?");

    Optional<ButtonType> result = alert.showAndWait();
    if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
      Platform.exit();
    }
  }

  /**
   * Displays an information alert.
   */
  public void aboutAlert() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Paths");
    alert.setHeaderText("Paths Engine");
    alert.setContentText("""
        Version: 2023-05-23
        Author: Jan Christian Nordskog & Vegard Arnesen Mytting
        """);
    alert.showAndWait();
  }
}