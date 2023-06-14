package no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal.Goal;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.logic.FileHandler;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.logic.Game;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.logic.Story;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.dialogs.GoalDialog;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.dialogs.PlayerDialog;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.scenes.CreateGameScene;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.scenes.GameScene;

/**
 * Represents a controller for {@link CreateGameScene}.
 * <p>
 * The controller is responisble for handling events in the scene. Events
 * such as:
 * <ul>
 * <li>Opening a file from the file-explorer.</li>
 * <li>Saves the chosen file to the pre-determined data-directory.</li>
 * <li>Creates a player from the {@link PlayerDialog}.</li>
 * <li>Creates goals from the {@link GoalDialog}.</li>
 * <li>Starts the game.</li>
 * </ul>
 * </p>
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class CreateGameSceneController {
  private CreateGameScene createGameScene;
  private MainMenuAppController mainMenuAppController;
  private FileHandler fileHandler;
  private Path dataDirectory;
  private GameScene gameScene;
  private File file;

  private static final String DATA_DIRECTORY = "src/main/resources/saved-stories/";

  /**
   * Constructs an instance of the CreateGameSceneController.
   *
   * @param createGameScene         the scene this controller is responisble for.
   * @param mainMenuSceneController the main application controller.
   */
  public CreateGameSceneController(CreateGameScene createGameScene,
      MainMenuAppController mainMenuSceneController) {
    this.createGameScene = createGameScene;
    this.mainMenuAppController = mainMenuSceneController;
    this.fileHandler = new FileHandler();
    this.gameScene = new GameScene(this.mainMenuAppController);
  }

  /**
   * Creates a data-dorectory if it already does not exist.
   *
   * @throws IOException if an I/O error occurs.
   */
  public void createDataDirectory() throws IOException {
    this.dataDirectory = Path.of(DATA_DIRECTORY);
    if (!Files.exists(this.dataDirectory)) {
      Files.createDirectory(this.dataDirectory);
    }
  }

  /**
   * Opens a file-explorer and gives the user a choice to select a "*.paths" file.
   * 
   * <p>
   * It is not possible to open corrupted file, or play with 
   * files that contains broken links or dead-end passages.
   * </p>
   *
   * @param event the action triggered by the user.
   */
  public void doOpenFile(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Paths Files", "*.paths"));
    fileChooser.setInitialDirectory(Path.of(DATA_DIRECTORY).toFile());
    this.file = fileChooser.showOpenDialog(this.createGameScene.getStage());
    if (this.file != null) {
      try {
        Story story = this.fileHandler.readStory(this.file.toPath());
        this.mainMenuAppController.setStory(story);
        this.createGameScene.showDataFile(this.file.getAbsolutePath());
        this.createGameScene.showAllItemsInStory(story);
        this.createGameScene.getStoryTitLabel().setText(story.getTitle());

        if (!story.getBrokenLinks().isEmpty()) {
          this.ifBrokenLinksAlert();
        } else if (!story.getDeadEndPassages().isEmpty()) {
          this.ifDeadEndPassagesAlert();
        }
      } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not read file");
        alert.setContentText("Please select another file, or fix the corrupted file.");
        alert.showAndWait();
      }
    }
  }

  /**
   * Saves a already selected file from the 
   * {@link CreateGameSceneController#doOpenFile(ActionEvent) doOpenFile-Method} 
   * to the pre-determined data-directory.
   *
   * @param event the action triggered by the user.
   */
  public void doSaveFile(ActionEvent event) {
    if (this.file != null) {
      try {
        this.createDataDirectory();

        String targetFileName = this.file.getName();
        Path targetFilePath = Path.of(DATA_DIRECTORY, targetFileName);
        Path source = this.file.toPath();

        Files.copy(source, targetFilePath, StandardCopyOption.REPLACE_EXISTING);

      } catch (IOException ioe) {
        ioe.getMessage();
      }
    }
  }

  /**
   * Creates a start player from the {@link PlayerDialog} class.
   *
   * @param event the action triggered by the user.
   */
  public void doCreationOfPlayer(ActionEvent event) {
    PlayerDialog playerDialog = new PlayerDialog();
    Optional<Player> playerResponse = playerDialog.showAndWait();

    try {
      if (playerResponse.isPresent()) {
        Player player = playerResponse.get();
        this.mainMenuAppController.setPlayer(player);
        this.mainMenuAppController.createStartPlayer();
        this.createGameScene.getPlayerValueName().setText(player.getName());
        this.createGameScene.getPlayerValueHealth().setText(String.valueOf(player.getHealth()));
        this.createGameScene.getPlayerValueGold().setText(String.valueOf(player.getGold()));
        this.createGameScene.getPlayerValueScore().setText(String.valueOf(player.getScore()));
        this.createGameScene.getPlayerValueInventory().setText(player.getInventoryAsString());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Player has been created");
        alert.setContentText("Player was created as:"
            + "\nName: " + this.mainMenuAppController.getPlayer().getName()
            + "\nHealth: " + this.mainMenuAppController.getPlayer().getHealth()
            + "\nGold: " + this.mainMenuAppController.getPlayer().getGold()
            + "\nScore: " + this.mainMenuAppController.getPlayer().getScore()
            + "\nInventory: " + this.mainMenuAppController.getPlayer().getInventory());
        alert.showAndWait();
      }
    } catch (IndexOutOfBoundsException ioobe) {
      ioobe.getMessage();
    }
  }

  /**
   * Creates start goals from the {@link GoalDialog} class.
   *
   * @param event the action triggered by the user.
   */
  public void doCreationOfGoals(ActionEvent event) {
    GoalDialog goalDialog = new GoalDialog();
    Optional<List<Goal>> goalsResponse = goalDialog.showAndWait();

    try {
      if (goalsResponse.isPresent()) {
        List<Goal> goals = goalsResponse.get();
        this.mainMenuAppController.setGoals(goals);
        this.mainMenuAppController.setStartGoals();
        this.createGameScene.getGoalValueHealth().setText(
            this.mainMenuAppController.getSpecificGoal("health goal").getGoalValueAsString());
        this.createGameScene.getGoalValueGold().setText(
            this.mainMenuAppController.getSpecificGoal("gold goal").getGoalValueAsString());
        this.createGameScene.getGoalValueScore().setText(
            this.mainMenuAppController.getSpecificGoal("score goal").getGoalValueAsString());
        this.createGameScene.getGoalValueInventory().setText(
            this.mainMenuAppController.getSpecificGoal("inventory goal").getGoalValueAsString());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Goals have been created");
        alert.setContentText("Goals were created as:"
            + "\nHealth Goal: "
            + this.mainMenuAppController.getSpecificGoal("health goal").getGoalValueAsString()
            + "\nGold Goal: "
            + this.mainMenuAppController.getSpecificGoal("gold goal").getGoalValueAsString()
            + "\nScore Goal: "
            + this.mainMenuAppController.getSpecificGoal("score goal").getGoalValueAsString()
            + "\nInventory Goal: "
            + this.mainMenuAppController.getSpecificGoal("inventory goal").getGoalValueAsString());
        alert.showAndWait();
      }
    } catch (IndexOutOfBoundsException ioobe) {
      ioobe.getMessage();
    }
  }

  /**
   * Creates a new game.
   */
  public void createGame() {
    try {
      this.mainMenuAppController.setGame(new Game(
          this.mainMenuAppController.getPlayer(),
          this.mainMenuAppController.getStory(),
          this.mainMenuAppController.getGoals()));
    } catch (IndexOutOfBoundsException ioobe) {
      ioobe.getMessage();
    }
  }

  /**
   * Checks all nessecary infomation is in place.
   *
   * @param event the action triggered by the user.
   */
  public void startGame(ActionEvent event) {
    if (this.mainMenuAppController.getStory() == null) {
      this.ifStoryIsNullAlert();
    } else if (this.mainMenuAppController.getPlayer() == null) {
      this.ifPlayerIsNullAlert();
    } else if (this.mainMenuAppController.getGoals() == null) {
      this.ifGoalsAreNullAlert();
    } else if (!this.mainMenuAppController.getStory().getBrokenLinks().isEmpty()) {
      this.ifBrokenLinksAlert();
    } else if (!this.mainMenuAppController.getStory().getDeadEndPassages().isEmpty()) {
      this.ifDeadEndPassagesAlert();
    } else {
      this.checkIfItemExists();
    }
  }

  /**
   * Displays an error alert when no story is selected.
   */
  private void ifStoryIsNullAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Could not find a story to play.");
    alert.setContentText("Please make sure you have selected a file to play.");
    alert.showAndWait();
  }

  /**
   * Displays an error alert when no player is created.
   */
  private void ifPlayerIsNullAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Could not find a player to play.");
    alert.setContentText("Please make sure you have created a player, with at least a name.");
    alert.showAndWait();
  }

  /**
   * Displays an error alert when no goals are created.
   */
  private void ifGoalsAreNullAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Could not find goals to play.");
    alert.setContentText("Please make sure you have created goals.");
    alert.showAndWait();
  }

  /**
   * Displays a warning alert if there are broken links in the file.
   */
  private void ifBrokenLinksAlert() {
    List<String> brokenLinksReferences = new LinkedList<>();
    for (int i = 0; i < this.mainMenuAppController.getStory().getBrokenLinks().size(); i++) {
      brokenLinksReferences.add("\n" + (i + 1) + ") " + this.mainMenuAppController.getStory()
          .getBrokenLinks().get(i).getReference());
    }

    Alert alert = new Alert(Alert.AlertType.WARNING,
        "The following passages does not exist: "
            + brokenLinksReferences
            + "\n\nDo you wish to fix the file in the File Editor?",
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Warning");
    alert.setHeaderText("File contains broken links");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.YES) {
      this.sendUserToFileEditorWithChosenFile();
    }
  }

  /**
   * Displays a warning alert if there are dead-end passages in the file.
   */
  private void ifDeadEndPassagesAlert() {
    List<String> deadEndPassageTitles = new LinkedList<>();
    for (int i = 0; i < this.mainMenuAppController.getStory().getDeadEndPassages().size(); i++) {
      deadEndPassageTitles.add("\n" + (i + 1) + ") " + this.mainMenuAppController.getStory()
          .getDeadEndPassages().get(i).getTitle());
    }

    Alert alert = new Alert(Alert.AlertType.WARNING,
        "\nThe following passages has no links: "
            + deadEndPassageTitles
            + "\n\nDo you wish to fix the file in the File Editor?",
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Warning");
    alert.setHeaderText("File contains dead-end passages");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.YES) {
      this.sendUserToFileEditorWithChosenFile();
    }
  }

  /**
   * Checks if gaols are possible to obtain.
   */
  private void checkIfItemExists() {
    this.fileHandler.getAllItems().addAll(this.mainMenuAppController.getPlayer().getInventory());
    if (this.fileHandler.getAllItems().containsAll(
        this.mainMenuAppController.getSpecificGoal("inventory goal").getList())) {
      this.createGame();
      this.mainMenuAppController.switchScene(this.gameScene.createGameScene());
    } else {
      ifItemDoesNotExistsAlert();
    }
  }

  /**
   * Diaplays a error alert if an item does not exist in the file.
   */
  private void ifItemDoesNotExistsAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Item Does Not Exist.");

    List<String> inventoryGoalList = new LinkedList<>(
        this.mainMenuAppController.getSpecificGoal("inventory goal").getList());

    List<String> allItemList = new LinkedList<>(this.fileHandler.getAllItems());

    allItemList.retainAll(
        this.mainMenuAppController.getSpecificGoal("inventory goal").getList());

    inventoryGoalList.removeAll(allItemList);

    String allNonExistingItems = "";
    for (String items : inventoryGoalList) {
      allNonExistingItems += items.strip() + ", ";
    }

    alert.setContentText(
        "Inventory goal contains at least one item that does not exist in the game. "
            + "Please change your goals to match what the game can offer. "
            + "Items that does not match are: " + allNonExistingItems);
    alert.showAndWait();
  }

  /**
   * Sends the user to {@link FileEditorScene} with the corrupted file.
   */
  private void sendUserToFileEditorWithChosenFile() {
    try {
      this.mainMenuAppController.switchScene(
          this.mainMenuAppController.getMainMenuApp().getFileEditorScene().createEditFileScene());
      String fileContent = Files.readString(this.file.toPath());
      this.mainMenuAppController.getMainMenuApp().getFileEditorScene()
          .getEditFileSceneController().setFile(this.file);
      this.mainMenuAppController.getMainMenuApp().getFileEditorScene()
          .getTextArea().setText(fileContent);
      this.mainMenuAppController.clearGame();
    } catch (IOException ioe) {
      ioe.getMessage();
    }
  }

  /**
   * Returns the GameScene.
   *
   * @return the GameScene.
   */
  public GameScene getGameScene() {
    return this.gameScene;
  }

  /**
   * Returns the FileHandler.
   *
   * @return the FileHandler.
   */
  public FileHandler getFileHandler() {
    return this.fileHandler;
  }
}